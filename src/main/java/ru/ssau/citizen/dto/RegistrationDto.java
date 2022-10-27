package ru.ssau.citizen.dto;

import lombok.Data;

@Data
public class RegistrationDto {

    private String username;
    private String password;
    private String email;
    private String city;
    private String street;
    private String house;
    private String apartment;

    private String role;
}
