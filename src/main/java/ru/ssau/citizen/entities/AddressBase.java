package ru.ssau.citizen.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class AddressBase {

    @Id
    @GeneratedValue
    private Long id;
}
