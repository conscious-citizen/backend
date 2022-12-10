package ru.ssau.citizen.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.io.File;
import java.time.LocalDate;
import java.util.Base64;

@Data
@Entity
@Table(name = "events")
@Schema(description = "Информация об инциденте")
public class Event {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Actor actor;

//    @ManyToOne
    private Long rubricId;

    @Null
    @OneToOne
    private Address address;

    @OneToOne
    private Template template;

    private boolean status;

    private String messageSubject;

    private String messageText;

    @ManyToOne
    private Photo photo;

    private LocalDate currentDate;

    private boolean result;

}
