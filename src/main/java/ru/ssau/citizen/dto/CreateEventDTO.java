package ru.ssau.citizen.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateEventDTO {

    private String messageSubject;

    private String messageText;

    private byte[] photo;
}
