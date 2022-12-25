package ru.ssau.citizen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssau.citizen.entities.Address;
import ru.ssau.citizen.entities.Event;
import ru.ssau.citizen.entities.Photo;
import ru.ssau.citizen.entities.EventDraft;
import ru.ssau.citizen.repository.AddressRepository;
import ru.ssau.citizen.repository.EventDraftRepository;

import ru.ssau.citizen.repository.EventRepository;
import ru.ssau.citizen.repository.PhotoRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;
    private final AddressRepository addressRepository;
    private final PhotoRepository photoRepository;



    private final EventDraftRepository eventDraftRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, AddressRepository addressRepository, PhotoRepository photoRepository, EventDraftRepository eventDraftRepository) {
        this.eventRepository = eventRepository;
        this.addressRepository = addressRepository;
        this.photoRepository = photoRepository;
        this.eventDraftRepository = eventDraftRepository;
    }

    @Override
    public void createEvent(Event event, Address address) {
        Address address1 = addressRepository.save(address);
        event.setAddress(address1);
        event.setStatus(false);
        event.setCurrentDate(LocalDate.now());
        List<Photo> photos = event.getPhoto();
        Event savedEvent = eventRepository.save(event);
        for (Photo photo: photos
             ) {
            photo.setEvent(savedEvent);
            photoRepository.save(photo);
        }

        addressRepository.save(address1);
    }

    @Override
    public void createEventDraft(EventDraft event, Address address) {
        /*Address address1 = addressRepository.save(address);
        event.setAddress(address1);
        event.setStatus(false);
        event.setCurrentDate(LocalDate.now());
        List<Photo> photos = event.getPhoto();
        for (Photo photo: photos
        ) {
            photo.setEventDraft(event);
            photoRepository.save(photo);
        }
        save(event);
        addressRepository.save(address1);*/
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
