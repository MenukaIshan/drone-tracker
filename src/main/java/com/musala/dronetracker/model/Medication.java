package com.musala.dronetracker.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Data
@Entity
public class Medication {

    @GeneratedValue
    @Column(name = "medicine_id")
    private int medicineId;

    @Column(name = "name")
    @Pattern(regexp = "^[a-zA-Z0-9_-]*$")
    private String name;

    @Column(name = "weight")
    private float weight;

    @Id
    @Column(name = "code")
    @Pattern(regexp = "^[A-Z0-9_]*$")
    private String code;

    @Column(name = "image")
    private String image;
}
