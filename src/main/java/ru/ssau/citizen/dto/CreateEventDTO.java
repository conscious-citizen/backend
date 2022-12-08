package ru.ssau.citizen.dto;

import lombok.Data;
import ru.ssau.citizen.entities.Address;

@Data
public class CreateEventDTO {

    private String messageSubject;

    private String messageText;

    private byte[] photo;

    private AddressDto addressDto;

    private Long rubricId;
}
