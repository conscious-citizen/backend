package ru.ssau.citizen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ssau.citizen.entities.Address;
import ru.ssau.citizen.entities.Event;
import ru.ssau.citizen.entities.EventDraft;
import ru.ssau.citizen.entities.Rubric;
import ru.ssau.citizen.repository.AddressRepository;
import ru.ssau.citizen.repository.EventDraftRepository;
import ru.ssau.citizen.repository.EventRepository;

import java.sql.Blob;
import java.time.LocalDate;

@Service
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;
    private final AddressRepository addressRepository;

    private final EventDraftRepository eventDraftRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository,EventDraftRepository eventDraftRepository, AddressRepository addressRepository) {
        this.eventDraftRepository = eventDraftRepository;
        this.eventRepository = eventRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void createEvent(Event event, Address address) {
        Address address1 = addressRepository.save(address);
        event.setAddress(address1);
        event.setStatus(false);
        event.setCurrentDate(LocalDate.now());
        save(event);

        address1.setEvent(event);
        addressRepository.save(address1);
    }

    @Override
    public void createEventDraft(EventDraft event, Address address) {
        Address address1 = addressRepository.save(address);
        event.setAddress(address1);
        event.setStatus(false);
        event.setCurrentDate(LocalDate.now());
        save(event);

        address1.setEventDraft(event);
        addressRepository.save(address1);
    }

    @Override
    public void save(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void save(EventDraft eventDraft) {
        eventDraftRepository.save(eventDraft);
    }

}
