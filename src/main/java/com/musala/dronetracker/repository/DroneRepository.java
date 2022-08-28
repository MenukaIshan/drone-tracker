package com.musala.dronetracker.repository;

import com.musala.dronetracker.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, Integer> {
    @Modifying
    @Transactional
    @Query(value = "insert into drone(model, serial_number, weight, battery_percentage, state)" +
            " VALUES (:model,:serial_number,:weight, :battery_percentage, :state )", nativeQuery = true)
    public void saveNewDrone(@Param("model") String model,
                             @Param("serial_number") String serialNumber,
                             @Param("weight") float weight,
                             @Param("battery_percentage") float batteryPercentage,
                             @Param("state") String state);


    @Modifying
    @Transactional
    @Query(value = "insert into drone_medication(drone_id, code, loaded_date_time) values (:droneId, :medicine_code, now())",
            nativeQuery = true)
    public void loadMedicationToDrone(@Param("droneId") Integer droneId,
                                      @Param("medicine_code") String medicine_code);

    List<Drone> findByStateIn(List<String> states);
}
