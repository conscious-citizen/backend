package ru.ssau.citizen.dto;

import lombok.Data;

@Data
public class ResetPasswordDTO {


    String token;
    String password;

}
