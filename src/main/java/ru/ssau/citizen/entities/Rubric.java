package ru.ssau.citizen.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Rubric {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "rubric")
    List<Event> events;
}
