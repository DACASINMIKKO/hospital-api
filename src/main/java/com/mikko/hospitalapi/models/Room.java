package com.mikko.hospitalapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ROOM_NUMBER")
    private Integer roomNumber;
    @Column(name = "AIRCONDITIONED")
    private Boolean isAirconditioned;

    @OneToOne(mappedBy = "room")
    @JsonIgnoreProperties(value = {"room"}, allowSetters = true)
    private Patient patient;

    public void addPatient(Patient patient) {
        this.patient = patient;
    }
}
