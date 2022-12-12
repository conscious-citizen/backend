package ru.ssau.citizen.dto;

import lombok.Data;
import ru.ssau.citizen.entities.Photo;

import java.util.List;

@Data
public class CreateEventDTO {

    private String messageSubject;

    private String messageText;

    private List<PhotoDTO> photo;

    private AddressDto addressDto;

    private Long rubricId;
}
