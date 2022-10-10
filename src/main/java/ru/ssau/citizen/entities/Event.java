package ru.ssau.citizen.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Entity
public class Event {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Actor actor;

    @ManyToOne
    private Rubric rubric;

    private boolean status;

    private String messageSubject;

    private String messageText;

    private String photo;

    private LocalDate currentDate;

    private boolean result;

}
