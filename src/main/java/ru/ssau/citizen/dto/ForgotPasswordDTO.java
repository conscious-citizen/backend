package ru.ssau.citizen.dto;

import lombok.Data;

@Data
public class ForgotPasswordDTO {
    private String email;
    private String url;
}