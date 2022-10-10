package ru.ssau.citizen.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    Event event;
}
