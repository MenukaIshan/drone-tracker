package com.musala.dronetracker.service.impl;

import com.musala.dronetracker.model.DTO;
import com.musala.dronetracker.model.Drone;
import com.musala.dronetracker.repository.DroneRepository;
import com.musala.dronetracker.service.DroneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DroneServiceImpl implements DroneService {

    private static final Logger logger = LoggerFactory.getLogger(DroneServiceImpl.class);
    private final DroneRepository droneRepository;

    @Autowired
    public DroneServiceImpl(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public ResponseEntity getAllDrones() {
        logger.info("Service: Get all drones request");
        List<Drone> droneList = droneRepository.findAll();
        DTO dto = new DTO(HttpStatus.OK, "Success", droneList);
        logger.info("Service: [{}] of Drones received from database", droneList.size());
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity getDroneDetails(Integer droneId) {
        logger.info("Service: Drone Search request for id [{}] is received", droneId);

        DTO dto = null;
        Optional<Drone> droneOptional = droneRepository.findById(droneId);

        if (droneOptional.isPresent()) {
            logger.info("Service: Drone found for id - [{}]", droneId);
            dto = new DTO(HttpStatus.OK, "Success", droneOptional.get());
        } else {
            logger.info("Service: Drone not found for id - [{}]", droneId);
            dto = new DTO(HttpStatus.NO_CONTENT, "Drone not found", null);
        }

        return ResponseEntity.ok(dto);
    }
}
