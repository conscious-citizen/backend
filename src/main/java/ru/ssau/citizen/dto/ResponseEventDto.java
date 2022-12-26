package ru.ssau.citizen.dto;

import lombok.Data;
import ru.ssau.citizen.entities.Actor;
import ru.ssau.citizen.entities.Address;
import ru.ssau.citizen.entities.Event;
import ru.ssau.citizen.entities.Template;

import java.time.LocalDate;

@Data
public class ResponseEventDto {
    private Long id;
    private Actor actor;
    private Long rubricId;
    private Address address;
    private Template template;
    private boolean status;
    private String messageSubject;
    private String messageText;
    private LocalDate currentDate;
    private boolean result;
    private String[] photoUrls;

    public ResponseEventDto(Event event) {
        id = event.getId();
        actor = event.getActor();
        rubricId = event.getRubricId();
        address = event.getAddress();
        template = event.getTemplate();
        status = event.isStatus();
        messageSubject = event.getMessageSubject();
        messageText = event.getMessageText();
        currentDate = event.getCurrentDate();
        result = event.isResult();
    }
}
