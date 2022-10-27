package ru.ssau.citizen.DTO;

import lombok.Data;

import java.net.URL;

@Data
public class ForgotPasswordDTO {
    private String email;
    private String url;
}