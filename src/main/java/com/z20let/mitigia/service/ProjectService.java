package com.z20let.mitigia.service;

import com.z20let.mitigia.model.Project;
import com.z20let.mitigia.model.ProjectDTO;
import com.z20let.mitigia.model.Vehicle;
import com.z20let.mitigia.repository.ProjectRepository;
import com.z20let.mitigia.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProjectService {

    private final VehicleService vehicleService;
    private ProjectRepository projectRepository;
    private VehicleRepository vehicleRepository;

    public void saveProject(MultipartFile file) {
        if (ExcelToDatabaseService.validateExcelFile(file)) {
            try {
                List<Project> projects = ExcelToDatabaseService.getDataFromExcelFile(file.getInputStream());
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
}
