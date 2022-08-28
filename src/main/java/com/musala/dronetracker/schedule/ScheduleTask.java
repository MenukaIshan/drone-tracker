package com.musala.dronetracker.schedule;

import com.musala.dronetracker.model.Drone;
import com.musala.dronetracker.model.DroneBatteryStatus;
import com.musala.dronetracker.repository.DroneHistoryRepository;
import com.musala.dronetracker.repository.DroneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.musala.dronetracker.model.State.IDLE;
import static com.musala.dronetracker.model.State.RETURNING;

@Component
public class ScheduleTask {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    private DroneRepository droneRepository;
    private DroneHistoryRepository droneHistoryRepository;

    @Autowired
    public ScheduleTask(DroneRepository droneRepository, DroneHistoryRepository droneHistoryRepository) {
        this.droneRepository = droneRepository;
        this.droneHistoryRepository = droneHistoryRepository;
    }

    @Scheduled(fixedDelayString = "60000")
    public void checkDroneBatteryStatus() {
        List<Drone> droneList = droneRepository.findAll();
        logger.info("Scheduled: [{}] of Drones retrieved for check", droneList.size());

        for (Drone drone : droneList) {
            logger.info("Scheduled: Drone id [{}] has battery of [{}%]",
                    drone.getDroneId(), drone.getBatteryPercentage());
            DroneBatteryStatus droneBatteryStatus = new DroneBatteryStatus(drone.getDroneId(),
                    drone.getBatteryPercentage());
            if (drone.getBatteryPercentage() < 25) {
                logger.warn("Scheduled: Drone id [{}] has low battery percentage of [{}%]",
                        drone.getDroneId(), drone.getBatteryPercentage());
                if (drone.getState().getStateValue().equals("DELIVERING") || drone.getState().getStateValue().equals("DELIVERED")) {
                    drone.setState(RETURNING);
                } else {
                    drone.setState(IDLE);
                }
                droneRepository.save(drone);
            }
            droneHistoryRepository.save(droneBatteryStatus);
        }

    }
}
