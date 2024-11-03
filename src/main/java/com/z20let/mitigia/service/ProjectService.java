package com.z20let.mitigia.service;

import com.z20let.mitigia.model.Project;
import com.z20let.mitigia.model.ProjectDTO;
import com.z20let.mitigia.model.Vehicle;
import com.z20let.mitigia.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProjectService {

    private final VehicleService vehicleService;
    private ProjectRepository projectRepository;
    private CarbonIntensityService carbonIntensityService;
    private DefaultECService defaultECService;


    public void saveProject(MultipartFile file) {
        if (com.z20let.mitigia.service.ExcelToDatabaseService.validateExcelFile(file)) {
            try {
                List<Project> projects = com.z20let.mitigia.service.ExcelToDatabaseService.getDataFromExcelFile(file.getInputStream());
                for (Project project : projects) {
                    Vehicle vehicle = project.getVehicle();
                    Optional<Vehicle> existingVehicleOpt = vehicleService.findById(vehicle.getVehicleId());

                    if (existingVehicleOpt.isPresent()) {
                        Vehicle existingVehicle = existingVehicleOpt.get();
                        existingVehicle.setLicensePlate(vehicle.getLicensePlate());
                        project.setVehicle(existingVehicle);
                    } else {
                        vehicleService.addLicensePlate(vehicle.getVehicleId(), vehicle.getLicensePlate());
                    }
                }
                projectRepository.saveAll(projects);
                calculateEmissions();
            } catch (IOException e) {
                throw new IllegalArgumentException("This is not a valid excel file");
            }
        }
    }


    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }


    public void createProject(ProjectDTO object) {
        Project project = new Project();
        project.setProjectId(object.getProjectId());
        project.setStartDate(object.getStartDate());
        project.setEndDate(object.getEndDate());
        project.setStartMileage(object.getStartOdometer());
        project.setEndMileage(object.getEndOdometer());
        project.setVehicle(vehicleService.addLicensePlate(object.getVehicleId(), object.getLicensePlate()));
        projectRepository.save(project);
    }

    public void updateProject(ProjectDTO object) {
        Project project = projectRepository.findById(object.getProjectId()).get();
        project.setProjectId(object.getProjectId());
        project.setStartDate(object.getStartDate());
        project.setEndDate(object.getEndDate());
        project.setStartMileage(object.getStartOdometer());
        project.setEndMileage(object.getEndOdometer());
        project.setVehicle(vehicleService.addLicensePlate(object.getVehicleId(), object.getLicensePlate()));
        projectRepository.save(project);
    }

    public Optional<Project> getProjectById(Integer id) {
        return projectRepository.findById(id);
    }

    public String deleteProjectById(Integer projectId) {
       projectRepository.deleteById(projectId);
       return "Project deleted successfully";
    }

    public void deleteAllProjects() {
        projectRepository.deleteAll();
    }

    public void calculateEmissions() {
        List<Project> projects = getAllProjects();
        for (Project project : projects) {
            Vehicle vehicle = project.getVehicle();
            int mileage = project.getEndMileage() - project.getStartMileage();
            int carbonIntensity = carbonIntensityService.getCarbonIntensity(project.getEndDate().getYear());
            int energyConsumption = getEnergyConsumption(vehicle);
            long carbonEmission = ((long) mileage) * energyConsumption * carbonIntensity;
            project.setCarbonEmission(carbonEmission);
            System.out.println(carbonEmission);
        }
        projectRepository.saveAll(projects);
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
            Integer defaultEnergyConsumption = defaultECService.getDefaultEC();
            energyConsumption = (defaultEnergyConsumption != null) ? defaultEnergyConsumption : 188; // default value in Wh/km
        }
        return energyConsumption;
    }

    public void calculateEmission(Project project) {
        int mileage = project.getEndMileage() - project.getStartMileage();
        int carbonIntensity = carbonIntensityService.getCarbonIntensity(project.getEndDate().getYear());
        int energyConsumption = getEnergyConsumption(project.getVehicle());
        long carbonEmission = ((long) mileage) * energyConsumption * carbonIntensity;
        project.setCarbonEmission(carbonEmission);
    }
}
