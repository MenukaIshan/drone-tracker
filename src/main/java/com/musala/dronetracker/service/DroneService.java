package com.musala.dronetracker.service;

import com.musala.dronetracker.model.Drone;
import com.musala.dronetracker.model.Medication;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DroneService {
    ResponseEntity getAllDrones();

    ResponseEntity getDroneDetails(Integer droneId);

    ResponseEntity saveDrone(Drone drone);

    ResponseEntity getBatteryStatus(Integer droneId);

    ResponseEntity checkMedicationForDrone(Integer droneId);

    ResponseEntity loadMedicationWithDrone(List<String> medicationList, Integer droneId);

    ResponseEntity checkAvailableDrones();
}
