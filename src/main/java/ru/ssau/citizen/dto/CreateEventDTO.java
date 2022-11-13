package ru.ssau.citizen.dto;

import lombok.Data;

@Data
public class CreateEventDTO {

    private String messageSubject;

    private String messageText;

    private String photo;
}
