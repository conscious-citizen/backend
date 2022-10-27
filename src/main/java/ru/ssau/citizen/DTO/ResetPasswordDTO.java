package ru.ssau.citizen.DTO;

import lombok.Data;

@Data
public class ResetPasswordDTO {


    String token;
    String password;

}
