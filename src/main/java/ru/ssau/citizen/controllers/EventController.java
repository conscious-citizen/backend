package ru.ssau.citizen.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.ssau.citizen.dto.CreateEventDTO;
import ru.ssau.citizen.entities.Actor;
import ru.ssau.citizen.entities.Address;
import ru.ssau.citizen.entities.Event;
import ru.ssau.citizen.entities.Rubric;
import ru.ssau.citizen.repository.ActorRepository;
import ru.ssau.citizen.repository.EventRepository;
import ru.ssau.citizen.service.EventService;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/event")
@Tag(name = "Инцидент", description = "Все методы для работы с инцидентами")
public class EventController {

    private final EventService eventService;
    private final ActorRepository actorRepository;
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public EventController(EventService eventService, ActorRepository actorRepository, EventRepository eventRepository, ModelMapper modelMapper) {
        this.eventService = eventService;
        this.actorRepository = actorRepository;
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @Operation(summary = "Создать инцидент")

    public ResponseEntity<Event> createEvent(@RequestBody CreateEventDTO createEventDTO,
                                                      Address address, Rubric rubric,
                                                      @AuthenticationPrincipal UserDetails userDetails) {
        Event event = convertToEvent(createEventDTO);
        event.setActor(actorRepository.findActorByLogin(userDetails.getUsername()));
        eventService.createEvent(event, address, rubric, createEventDTO.getPhoto());
        return ResponseEntity.ok(event);
    }

    @GetMapping
    @Operation(summary = "Показать список инцедентов")
    public ResponseEntity<List<Event>> showAllEvent() {
        List<Event> eventList = eventRepository.findAll();
        return ResponseEntity.ok(eventList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Показать информацию о выбранном по ID инцеденте")
    public ResponseEntity<Event> showEvent(@PathVariable ("id") Long id) {
        Event event = eventRepository.findById(id).orElse(null);
        return ResponseEntity.ok(event);
    }

    private Event convertToEvent(CreateEventDTO createEventDTO) {
        return modelMapper.map(createEventDTO, Event.class);
    }

}
