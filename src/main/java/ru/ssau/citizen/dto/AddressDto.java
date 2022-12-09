package ru.ssau.citizen.dto;

import lombok.Data;

@Data
public class AddressDto {

    private float longitude;
    private float latitude;
    private String city;
    private String street;
    private String home;

}
