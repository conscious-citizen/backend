package ru.ssau.citizen.dto;

import lombok.Data;
import ru.ssau.citizen.DTO.PhotoDTO;
import ru.ssau.citizen.entities.Address;
import ru.ssau.citizen.dto.AddressDto;

@Data
public class CreateEventDTO {

    private String messageSubject;

    private String messageText;

    private PhotoDTO[] photo;

    private AddressDto addressDto;

    private Long rubricId;
}
