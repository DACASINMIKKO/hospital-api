package com.mikko.hospitalapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "AGE")
    private Integer age;
    @Column(name = "GENDER")
    private String gender;
    @Column(name = "DECEASED")
    private Boolean isDeceased;
    @ManyToOne
    @JoinColumn(name = "DOCTOR_ID", referencedColumnName = "ID")
    @JsonIgnoreProperties(value = {"patients"}, allowSetters = true)
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "ROOM_ID", referencedColumnName = "ID")
    @JsonIgnoreProperties(value = {"patient"}, allowSetters = true)
    private Room room;


    public void addDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void addRoom(Room room) {
        this.room = room;
    }
}
