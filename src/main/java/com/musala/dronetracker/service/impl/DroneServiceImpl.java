package com.musala.dronetracker.service.impl;

import com.musala.dronetracker.model.DTO;
import com.musala.dronetracker.model.Drone;
import com.musala.dronetracker.model.Medication;
import com.musala.dronetracker.repository.DroneRepository;
import com.musala.dronetracker.repository.MedicationRepository;
import com.musala.dronetracker.service.DroneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DroneServiceImpl implements DroneService {

    private static final Logger logger = LoggerFactory.getLogger(DroneServiceImpl.class);
    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;

    @Autowired
    public DroneServiceImpl(DroneRepository droneRepository, MedicationRepository medicationRepository) {
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
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

    @Override
    public ResponseEntity saveDrone(Drone drone) {
        logger.info("Service: New Drone [{}] Insert request", drone);
        DTO dto = null;
        try {
            droneRepository.saveNewDrone(drone.getModel(), drone.getSerialNumber(), drone.getWeight(),
                    drone.getBatteryPercentage(), drone.getState());
            logger.info("Service: Drone [{}] Successfully saved", drone);
            dto = new DTO(HttpStatus.CREATED, "Drone was successfully added", drone);
        } catch (DataIntegrityViolationException die) {
            dto = new DTO(HttpStatus.CONFLICT, die.getMessage(), drone);
            logger.error("Service: New drone add failed due to [{}]", die.getMessage());
            die.printStackTrace();
        } catch (Exception e) {
            dto = new DTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), drone);
            logger.error("Service: New drone add failed due to [{}]", e.getMessage());
            e.printStackTrace();
        }
        return new ResponseEntity(dto, dto.getHttpStatus());
    }

    @Override
    public ResponseEntity getBatteryStatus(Integer droneId) {
        logger.info("Service: Drone battery percentage checking for [{}]", droneId);
        Optional<Drone> droneOptional = droneRepository.findById(droneId);
        DTO dto = null;
        if (droneOptional.isPresent()) {
            logger.info("Service: Drone found for id - [{}]", droneId);

            Map<String, String> battery = new HashMap<>();
            battery.put("battery", droneOptional.get().getBatteryPercentage() + "%");
            dto = new DTO(HttpStatus.OK, "Success", battery);
        } else {
            logger.warn("Service: No Drone found for id [{}]", droneId);

            dto = new DTO(HttpStatus.NOT_FOUND, "No drone found for id - " + droneId, null);
        }
        return new ResponseEntity(dto, dto.getHttpStatus());
    }

    @Override
    public ResponseEntity checkMedicationForDrone(Integer droneId) {
        DTO dto = null;
        Optional<Drone> droneOptional = droneRepository.findById(droneId);
        if (droneOptional.isPresent()) {
            List<Medication> medicationList = droneOptional.get().getMedications();

            logger.info("Service: Drone found for id - [{}]", droneId);
            logger.info("Service: [{}] of Medication was in Drone id - [{}]",
                    medicationList.size(), droneId);


            if (medicationList.isEmpty()) {
                dto = new DTO(HttpStatus.OK, "No Medication was loaded for drone id - " + droneId,
                        medicationList);
            } else {
                dto = new DTO(HttpStatus.OK, "Medications found for drone id - " + droneId,
                        droneOptional.get().getMedications());
            }
        } else {
            logger.warn("Service: No Drone found for id [{}]", droneId);
            dto = new DTO(HttpStatus.NOT_FOUND, "No drone found for id - " + droneId, null);
        }
        return new ResponseEntity(dto, dto.getHttpStatus());
    }

    @Override
    public ResponseEntity loadMedicationWithDrone(List<String> medicationList, Integer droneId) {
        DTO dto = null;
        List<Medication> medicationsInDatabase = getMedicationInDatabase(medicationList);
        if (medicationsInDatabase.size() != medicationList.size()) {
            Map<String, List> medicineData = new HashMap<>();
            medicineData.put("requested_medicine_list", medicationList);
            medicineData.put("actual_medicine_list", medicationsInDatabase);
            dto = new DTO(HttpStatus.NOT_FOUND, "Some Medicines are not in database", medicineData);
            return new ResponseEntity(dto, dto.getHttpStatus());
        }

        Optional<Drone> droneOptional = droneRepository.findById(droneId);

        if (droneOptional.isPresent()) {
            Drone drone = droneOptional.get();
            String droneStatus = drone.getState();

            if (!(droneStatus.equals("IDLE") || droneStatus.equals("LOADING"))) {
                dto = new DTO(HttpStatus.CONFLICT, "Drone is not in a loadable status", drone);
                return new ResponseEntity(dto, dto.getHttpStatus());
            }

            List<Medication> medicineInDrone = drone.getMedications();
            List<Medication> combineMedicineList = new ArrayList<>();
            combineMedicineList.addAll(medicineInDrone);
            combineMedicineList.addAll(medicationsInDatabase);

            float totalMedicineWeight = getTotalMedicineWeight(combineMedicineList);

            if (drone.getBatteryPercentage() < 25) {
                logger.warn("Service: Battery is lower than 25% in drone id [{}]", droneId);
                dto = new DTO(HttpStatus.CONFLICT,
                        "Battery is " + drone.getBatteryPercentage() + "% for drone id - " + droneId,
                        drone);
                return new ResponseEntity(dto, dto.getHttpStatus());
            }
            if (totalMedicineWeight > 500) {
                logger.warn("Service: Total Medicine weight [{}] is over 500g", totalMedicineWeight);
                dto = new DTO(HttpStatus.CONFLICT,
                        "Total Medicine  weight - " + totalMedicineWeight + " overweight drone capacity",
                        null);
                return new ResponseEntity(dto, dto.getHttpStatus());
            }

            for (String code : medicationList) {
                droneRepository.loadMedicationToDrone(droneId, code);
            }
            drone.setWeight(totalMedicineWeight);
            droneRepository.save(drone);
            dto = new DTO(HttpStatus.CREATED, "Success", droneRepository.findById(droneId));
        } else {
            logger.warn("Service: No Drone found for id [{}]", droneId);
            dto = new DTO(HttpStatus.NOT_FOUND, "No drone found for id - " + droneId, null);
        }
        return new ResponseEntity(dto, dto.getHttpStatus());
    }

    @Override
    public ResponseEntity checkAvailableDrones() {
        logger.info("Service: Request to check available drones for loading medicine is processing");
        DTO dto = null;
        List<Drone> droneCouldLoad = droneRepository.findByStateIn(Arrays.asList("IDLE", "LOADING"));

        List<Drone> droneWhichAreFeasibleForLoading = droneCouldLoad.stream()
                .filter(drone -> drone.getBatteryPercentage() > 25 && drone.getWeight() < 500)
                .collect(Collectors.toList());

        if (droneWhichAreFeasibleForLoading.isEmpty()) {
            logger.warn("Service: No Drones found for loading medicines");
            dto = new DTO(HttpStatus.NOT_FOUND, "No Drone available for loading",
                    droneWhichAreFeasibleForLoading);
            return new ResponseEntity(dto, dto.getHttpStatus());
        }

        logger.info("Service: [{}] of drones are suitable for loading", droneWhichAreFeasibleForLoading.size());
        dto = new DTO(HttpStatus.OK, "Medicine loadable Drones were found",
                droneWhichAreFeasibleForLoading);

        return new ResponseEntity(dto, dto.getHttpStatus());
    }

    private List<Medication> getMedicationInDatabase(List<String> medicationList) {
        List<Medication> medicationsInDatabase = new ArrayList<>();
        for (String code : medicationList) {
            Optional<Medication> optionalMedication = medicationRepository.findById(code);
            if (optionalMedication.isPresent()) {
                medicationsInDatabase.add(optionalMedication.get());
            }
        }
        return medicationsInDatabase;
    }

    private float getTotalMedicineWeight(List<Medication> medications) {
        float totalMedicineWeight = 0F;
        for (Medication medication : medications) {
            totalMedicineWeight += medication.getWeight();
        }
        return totalMedicineWeight;
    }
}
