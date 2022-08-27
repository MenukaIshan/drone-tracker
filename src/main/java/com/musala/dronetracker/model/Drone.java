package com.musala.dronetracker.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Drone {
    @Id
    @Column(name = "drone_id")
    private int droneId;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "model")
    private String model;

    @Column(name = "weight")
    private float weight;

    @Column(name = "battery_percentage")
    private float batteryPercentage;

    @Column(name = "state")
    private String state;
}
