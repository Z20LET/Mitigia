package com.z20let.mitigia.service;

import com.z20let.mitigia.model.Project;
import com.z20let.mitigia.model.Vehicle;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class ProjectEmissionCalculatorService {
    private ProjectService projectService;
    private DefaultECService defaultECService;
    private CarbonIntensityService carbonIntensityService;

    public List<Project> calculateEmissions() {
        List<Project> projects = projectService.getAllProjects();
        for (Project project : projects) {
            Vehicle vehicle = project.getVehicle();
            int mileage = project.getEndMileage() - project.getStartMileage();
            int carbonIntensity = carbonIntensityService.getCarbonIntensityByYear(project.getEndDate().getYear());
            int energyConsumption = getEnergyConsumption(vehicle);
            project.setCarbonEmission(mileage * energyConsumption * carbonIntensity);
        }
        return projects;
    }

    private int getEnergyConsumption(Vehicle vehicle) {
        Integer energyConsumptionWLTP = vehicle.getEnergyConsumptionWLTP();
        Integer energyConsumptionNEDC = vehicle.getEnergyConsumptionNEDC();

        int energyConsumption = 0;

        if (energyConsumptionWLTP != null) {
            energyConsumption = energyConsumptionWLTP; // Wh/km
        } else if (energyConsumptionNEDC != null) {
            energyConsumption = energyConsumptionNEDC * 10; // Convert kWh/100km to Wh/km
        } else {
            energyConsumption = defaultECService.getDefaultEC(); // default value in Wh/km
        }
        return energyConsumption;
    }
}
