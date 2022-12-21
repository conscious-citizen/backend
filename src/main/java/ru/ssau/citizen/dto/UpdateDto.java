package ru.ssau.citizen.dto;

import lombok.Data;

@Data
public class UpdateDto {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String phoneNumber;

    private String city;

    private String street;

    private String house;

    private String apartment;

    private String login;
}
