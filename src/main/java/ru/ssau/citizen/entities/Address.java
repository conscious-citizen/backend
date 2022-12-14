package ru.ssau.citizen.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Null;

@Data
@Entity
@Schema(description = "Информация об адресе")
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Null
    @OneToOne
    Event event;
    @OneToOne
    EventDraft eventDraft;

    private float longitude;
    private float latitude;
    private String city;
    private String street;
    private String home;
}
