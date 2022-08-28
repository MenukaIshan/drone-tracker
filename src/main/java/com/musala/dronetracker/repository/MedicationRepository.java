package com.musala.dronetracker.repository;

import com.musala.dronetracker.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, String> {
}
