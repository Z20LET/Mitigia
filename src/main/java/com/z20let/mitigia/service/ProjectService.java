package com.z20let.mitigia.service;

import com.z20let.mitigia.model.Project;
import com.z20let.mitigia.model.Vehicle;
import com.z20let.mitigia.repository.ProjectRepository;
import com.z20let.mitigia.repository.VehicleRepository;
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


}
