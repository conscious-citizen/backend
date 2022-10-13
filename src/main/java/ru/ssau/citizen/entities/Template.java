package ru.ssau.citizen.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    Event event;

    private String name;
    private String text;
}
