package com.musala.dronetracker.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Medication {

    @GeneratedValue
    @Column(name = "medicine_id")
    private int medicineId;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private float weight;

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "image")
    private String image;
}
