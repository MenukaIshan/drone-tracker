package com.musala.dronetracker.controller;

import com.musala.dronetracker.model.Drone;
import com.musala.dronetracker.service.DroneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DispatchController {
    private static final Logger logger = LoggerFactory.getLogger(DispatchController.class);
    private final DroneService droneService;

    @Autowired
    public DispatchController(DroneService droneService) {
        this.droneService = droneService;
    }

    @GetMapping("/drones")
    public ResponseEntity getAllDrones() {
        logger.info("Controller: Get all drones request received");
        return droneService.getAllDrones();
    }

    @GetMapping("/drones/{droneId}")
    public ResponseEntity getADroneDetails(@PathVariable(name = "droneId", required = true) Integer droneId) {
        logger.info("Controller: Get drone for id [{}]", droneId);
        return droneService.getDroneDetails(droneId);
    }

    @PostMapping("/drones")
    public ResponseEntity addNewDrone(@RequestBody Drone drone) {
        logger.info("Controller: New drone creation request for drone [{}]", drone);
        return droneService.saveDrone(drone);
    }

    @GetMapping("/drones/{droneId}/battery-status")
    public ResponseEntity getDroneBatteryStatus(@PathVariable(name = "droneId", required = true) Integer droneId) {
        logger.info("Controller: Battery status check for drone id [{}] received", droneId);
        return droneService.getBatteryStatus(droneId);
    }

    @GetMapping("/drones/{droneId}/medications")
    public ResponseEntity getLoadedMedications(@PathVariable(name = "droneId", required = true) Integer droneId) {
        logger.info("Controller: Medication check request for drone id [{}] received", droneId);
        return droneService.checkMedicationForDrone(droneId);
    }

    @PostMapping("/drones/{droneId}/medications")
    public ResponseEntity loadDroneWithMedication(@RequestBody List<String> medicationList,
                                                  @PathVariable(name = "droneId", required = true) Integer droneId) {
        return droneService.loadMedicationWithDrone(medicationList, droneId);
    }

    @GetMapping("/drones/available")
    public ResponseEntity checkAvailableDrones() {
        logger.info("Controller: Request to check available drones for loading medicine");
        return droneService.checkAvailableDrones();
    }
}
