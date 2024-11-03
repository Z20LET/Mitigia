package com.z20let.mitigia.service;

import com.z20let.mitigia.model.OdometerReading;
import com.z20let.mitigia.model.Project;
import com.z20let.mitigia.model.Vehicle;
import com.z20let.mitigia.repository.OdometerReadingRepository;
import com.z20let.mitigia.repository.ProjectRepository;
import com.z20let.mitigia.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OdometerReadingService {

    private VehicleRepository vehicleRepository;
    private ProjectRepository projectRepository;
    private OdometerReadingRepository odometerRepository;

    public String updateOdometer(String licensePlate, String odometer, String date) {
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date);
        } catch (Exception e) {
            return "Provide a valid date format is 'yyyy-MM-dd'";
        }

        Optional<Vehicle> optionalVehicle = vehicleRepository.findByLicensePlate(licensePlate);
        if (optionalVehicle.isEmpty()) {
            return "License plate not found.";
        }
        Vehicle vehicle = optionalVehicle.get();

        Optional<Project> optionalProject = projectRepository.findByVehicle(vehicle);
        if (optionalProject.isEmpty()) {
            return "Project for given vehicle not found.";
        }
        Project project = optionalProject.get();

        if (localDate.isBefore(project.getEndDate()) || localDate.isBefore(project.getStartDate()) || localDate.isAfter(LocalDate.now())) {
            return "Provide a valid date";
        }

        int odometerInt;
        try {
            odometerInt = Integer.parseInt(odometer);
        } catch (NumberFormatException e) {
            return "Provide a valid odometer reading.";
        }
        if (odometerInt <= project.getEndMileage()) {
            return "Odometer reading must be greater than the current reading.";
        }
        project.setEndMileage(odometerInt);
        project.setEndDate(localDate);
        createOdometerReading(licensePlate, project.getEndMileage()-project.getStartMileage(), localDate);
        project.getVehicle().setMileage(odometerInt);
        this.projectRepository.save(project);
        return "Odometer updated successfully.";
    }

    public void createOdometerReading(String licensePlate, int odometer, LocalDate date){
        odometerRepository.save(new OdometerReading(licensePlate,odometer,date));
    }
}
