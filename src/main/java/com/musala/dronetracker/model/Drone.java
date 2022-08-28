package com.musala.dronetracker.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Drone {
    @Id
    @Column(name = "drone_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "drone_medication",
            joinColumns = {@JoinColumn(name = "drone_id")},
            inverseJoinColumns = {@JoinColumn(name = "code")}
    )
    private List<Medication> medications;

    @Override
    public String toString() {
        return "{" +
                "droneId=" + droneId +
                ", serialNumber='" + serialNumber + '\'' +
                ", model='" + model + '\'' +
                ", weight=" + weight +
                ", batteryPercentage=" + batteryPercentage +
                ", state='" + state + '\'' +
                ", medications=" + medications +
                '}';
    }
}
