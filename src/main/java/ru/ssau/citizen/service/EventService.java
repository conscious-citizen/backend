package ru.ssau.citizen.service;

import org.springframework.web.multipart.MultipartFile;
import ru.ssau.citizen.entities.Address;
import ru.ssau.citizen.entities.Event;
import ru.ssau.citizen.entities.EventDraft;
import ru.ssau.citizen.entities.Rubric;

public interface EventService {

    void createEvent(Event event, Address address);
    void createEventDraft(EventDraft event, Address address);

    void save(Event event);
    void save(EventDraft eventDraft);
}
