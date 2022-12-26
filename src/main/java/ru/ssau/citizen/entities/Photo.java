package ru.ssau.citizen.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.io.File;
import java.time.LocalDate;


@Entity
@Table(name = "photo")
@Data
@NoArgsConstructor
public class Photo {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Lob
    @Column(name = "data")
    @Type(type = "org.hibernate.type.ImageType")
    @JsonIgnore
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Photo(String name, String type, byte[] data, Event event) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.event = event;
    }

    public Photo(Integer id, String name, String type, byte[] data, Event event) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.data = data;
        this.event = event;
    }
}
