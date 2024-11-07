
package com.z20let.mitigia.service;

import com.z20let.mitigia.model.OdometerReading;
import com.z20let.mitigia.model.Project;
import com.z20let.mitigia.repository.OdometerReadingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OdometerReadingService {

    private ProjectService projectService;
    private OdometerReadingRepository odometerRepository;

    public String updateOdometer(String licensePlate, String odometer, String date) {
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date);
        } catch (Exception e) {
            return "Provide a valid date format is 'yyyy-MM-dd'";
        }

        Optional<Project> optionalProject = projectService.findProjectByLicensePlate(licensePlate);
        if (optionalProject.isEmpty()) {
            return "Provide a valid license plate, format is 'ABC123'";
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
        if (odometerInt <= project.getEndOdo()) {
            return "Odometer reading must be greater than the current reading.";
        }
        project.setEndOdo(odometerInt);
        project.setEndDate(localDate);
        createOdometerReading(licensePlate, project.getEndOdo()-project.getStartOdo(), localDate);

        return "Odometer updated successfully.";
    }

    public void createOdometerReading(String licensePlate, int odometer, LocalDate date){
        odometerRepository.save(new OdometerReading(licensePlate,odometer,date));
    }
}
