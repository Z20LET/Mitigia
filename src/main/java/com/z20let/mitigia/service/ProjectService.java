package com.z20let.mitigia.service;

import com.z20let.mitigia.model.Project;
import com.z20let.mitigia.model.ProjectDTO;
import com.z20let.mitigia.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Service
public class ProjectService {

    private final VehicleService vehicleService;
    private ProjectRepository projectRepository;


    public void saveProject(MultipartFile file) {
        if (ExcelToDatabaseService.validateExcelFile(file)) {
            try {
                List<Project> projects = ExcelToDatabaseService.getDataFromExcelFile(file.getInputStream());

                for (Project project : projects) {
                    vehicleService.updateLicensePlate(project.getVehicle().getVehicleId(), project.getVehicle().getLicensePlate());
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
        project.setStartMileage(object.getEndOdometer());
        project.setVehicle(vehicleService.updateLicensePlate(object.getVehicleId(), object.getLicensePlate()));
        projectRepository.save(project);
    }

}
