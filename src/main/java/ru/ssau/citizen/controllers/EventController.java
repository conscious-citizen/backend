package ru.ssau.citizen.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ssau.citizen.dto.*;
import ru.ssau.citizen.entities.*;
import ru.ssau.citizen.repository.ActorRepository;
import ru.ssau.citizen.repository.EventDraftRepository;
import ru.ssau.citizen.repository.EventRepository;
import ru.ssau.citizen.service.EventService;
import ru.ssau.citizen.service.PhotoService;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event")
@Tag(name = "Инцидент", description = "Все методы для работы с инцидентами")
public class EventController {

    private final EventService eventService;

    private final PhotoService photoService;
    private final ActorRepository actorRepository;
    private final EventRepository eventRepository;
    private final EventDraftRepository eventDraftRepository;
    private final ModelMapper modelMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Autowired
    public EventController(EventService eventService, ActorRepository actorRepository, EventRepository eventRepository, EventDraftRepository eventDraftRepository, ModelMapper modelMapper, PhotoService photoService) {
        this.eventService = eventService;
        this.photoService = photoService;
        this.actorRepository = actorRepository;
        this.eventRepository = eventRepository;
        this.eventDraftRepository = eventDraftRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @Operation(summary= "Создать новый инцидент")
    public ResponseEntity<MessageDto> uploadImage(@RequestParam("firstFile")MultipartFile firstFile,
                                                  @RequestParam(value = "secondFile", required = false)MultipartFile secondFile,
                                                  @RequestParam(value = "thirdFile", required = false)MultipartFile thirdFile,
                                                  @RequestParam("messageSubject")String messageSubject,
                                                  @RequestParam("messageText")String messageText,
                                                  @RequestParam("addressDto")String addressDto,
                                                  @RequestParam("rubricId")Long rubricId,
                                                  @AuthenticationPrincipal UserDetails userDetails
    ) throws IOException {
        CreateEventDto createEventDto = convertToEvent(firstFile, secondFile, thirdFile, messageSubject, messageText, addressDto, rubricId);
        Event event = convertToEvent(createEventDto);
        Address address = convertToAddress(createEventDto.getAddressDto());
        event.setActor(actorRepository.findActorByLogin(userDetails.getUsername()));
        eventService.createEvent(event, address);
        return ResponseEntity.ok(new MessageDto("Инцидент успешно создан"));
    }

    @GetMapping
    @Operation(summary = "Показать список инцедентов")
    public ResponseEntity<List<ResponseEventDto>> showAllEvent() {
        List<Event> eventList = eventRepository.findAll();
        List<ResponseEventDto> responseEventDtos = eventList.stream().map(ResponseEventDto::new).collect(Collectors.toList());
        for (ResponseEventDto item: responseEventDtos) {
            item.setPhotoUrls(photoService.getPhotosByIncident(item.getId()));
        }
        return ResponseEntity.ok(responseEventDtos);
    }

    @GetMapping("photos/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Photo photo = photoService.getPhoto(Integer.parseInt(id));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + photo.getName() + "\"")
                .body(photo.getData());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Показать информацию о выбранном по ID инцеденте")
    public ResponseEntity<Event> showEvent(@PathVariable("id") Long id) {
        Event event = eventRepository.findById(id).orElse(null);
        return ResponseEntity.ok(event);
    }
    @PostMapping("/createdraft")
    @Operation(summary = "Добавить черновик")

    public ResponseEntity<EventDraft> createEventDraft(@RequestBody CreateEventDto createEventDTO,
                                                       @AuthenticationPrincipal UserDetails userDetails) {
        EventDraft event = convertToEventDraft(createEventDTO);
        Address address = convertToAddress(createEventDTO.getAddressDto());
        event.setActor(actorRepository.findActorByLogin(userDetails.getUsername()));
        eventService.createEventDraft(event, address);
        return ResponseEntity.ok(event);
    }
    @GetMapping("/draft")
    @Operation(summary = "Показать список черновиков")
    public ResponseEntity<List<EventDraft>> showAllEventDraft() {
        List<EventDraft> eventList = eventDraftRepository.findAll();
        return ResponseEntity.ok(eventList);
    }
    @GetMapping("/draft/{id}")
    @Operation(summary = "Показать информацию о выбранном по ID инцеденте")
    public ResponseEntity<EventDraft> showEventDraft(@PathVariable("id") Long id) {
        EventDraft event = eventDraftRepository.findById(id).orElse(null);
        return ResponseEntity.ok(event);
    }

    private Event convertToEvent(CreateEventDto createEventDTO) {
        return modelMapper.map(createEventDTO, Event.class);
    }
    private CreateEventDto convertToEvent(MultipartFile firstFile,
                                          MultipartFile secondFile,
                                          MultipartFile thirdFile,
                                          String messageSubject,
                                          String messageText,
                                          String addressDto,
                                          Long rubricId) throws IOException {
        if (firstFile != null && secondFile != null && thirdFile != null)
            return new CreateEventDto(
                messageSubject,
                messageText,
                List.of(new PhotoDto[]{
                            new PhotoDto(firstFile.getOriginalFilename(), firstFile.getContentType(), firstFile.getBytes()),
                            new PhotoDto(secondFile.getOriginalFilename(), secondFile.getContentType(), secondFile.getBytes()),
                            new PhotoDto(thirdFile.getOriginalFilename(), thirdFile.getContentType(), thirdFile.getBytes()),}),
                objectMapper.readValue(addressDto, AddressDto.class),
                rubricId);
        if (firstFile != null && secondFile != null)
            return new CreateEventDto(
                    messageSubject,
                    messageText,
                    List.of(new PhotoDto[]{
                            new PhotoDto(firstFile.getOriginalFilename(), firstFile.getContentType(), firstFile.getBytes()),
                            new PhotoDto(secondFile.getOriginalFilename(), secondFile.getContentType(), secondFile.getBytes())}),
                    objectMapper.readValue(addressDto, AddressDto.class),
                    rubricId);
        else return new CreateEventDto(
                messageSubject,
                messageText,
                List.of(new PhotoDto[]{
                        new PhotoDto(firstFile.getOriginalFilename(), firstFile.getContentType(), firstFile.getBytes())}),
                objectMapper.readValue(addressDto, AddressDto.class),
                rubricId);
    }
    private EventDraft convertToEventDraft(CreateEventDto createEventDTO) {
        return modelMapper.map(createEventDTO, EventDraft.class);
    }
    private Address convertToAddress(AddressDto addressDto) {
        return modelMapper.map(addressDto, Address.class);
    }

}
