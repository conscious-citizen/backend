package ru.ssau.citizen.dto;

import lombok.Data;
import ru.ssau.citizen.entities.Photo;

import java.util.List;

@Data
public class PhotoDto {

    private String name;

    private String type;

    private byte[] data;

    public PhotoDto(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
}
