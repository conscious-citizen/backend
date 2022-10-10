package ru.ssau.citizen.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Institution {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String city;
    private String district;
    private String description;
}
