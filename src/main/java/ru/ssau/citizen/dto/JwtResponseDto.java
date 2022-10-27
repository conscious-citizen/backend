package ru.ssau.citizen.dto;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponseDto {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private List<String> role;

    public JwtResponseDto(String token, Long id, String username, List<String> role) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.role = role;
    }
}
