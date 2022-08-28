package com.musala.dronetracker.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class Drone {
    @Id
    @Column(name = "drone_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int droneId;

    @Column(name = "serial_number")
    @Size(max = 100, message = "Serial Number length exceed 100 character limit")
    private String serialNumber;

    @Column(name = "model")
    @Enumerated(EnumType.STRING)
    private Model model;

    @Column(name = "weight")
    @Max(value = 500, message = "Weight cannot exceed 500g")
    private float weight;

    @Column(name = "battery_percentage")
    private float batteryPercentage;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;

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
