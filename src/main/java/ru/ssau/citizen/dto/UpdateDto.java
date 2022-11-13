package ru.ssau.citizen.dto;

import lombok.Data;

@Data
public class UpdateDto {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String phoneNumber;
    private String username;
    private String street;
    private String email;
    private String city;
}
