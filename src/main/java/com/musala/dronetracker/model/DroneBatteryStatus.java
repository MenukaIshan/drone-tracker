package com.musala.dronetracker.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class DroneBatteryStatus {
    @Id
    @Column(name = "log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int logId;

    @Column(name = "drone_id")
    private int drone_id;

    @Column(name = "battery_percentage")
    private float batteryPercentage;

    public DroneBatteryStatus(int drone_id, float batteryPercentage) {
        this.drone_id = drone_id;
        this.batteryPercentage = batteryPercentage;
    }
}
