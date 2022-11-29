package ru.ssau.citizen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ssau.citizen.entities.Address;
import ru.ssau.citizen.entities.Event;
import ru.ssau.citizen.entities.Rubric;
import ru.ssau.citizen.repository.EventRepository;

import java.sql.Blob;
import java.time.LocalDate;

@Service
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void createEvent(Event event, Address address, Rubric rubric, byte[] photoDir) {
        event.setAddress(address);
        event.setStatus(false);
        event.setCurrentDate(LocalDate.now());
        event.setRubric(rubric);
        event.setPhoto(photoDir);
        save(event);
    }

    @Override
    public void save(Event event) {
        eventRepository.save(event);
    }

}
