package com.z20let.mitigia.service;

import com.z20let.mitigia.model.Project;
import com.z20let.mitigia.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ProjectService {
    private ProjectRepository projectRepository;

    public void saveProject(MultipartFile file) {
        if(ProjectUploadService.isValidExcelFile(file)){
            try {
                List<Project> projects = ProjectUploadService.getProjectDataFromExcel(file.getInputStream());
                this.projectRepository.saveAll(projects);
            } catch (IOException e) {
                throw new IllegalArgumentException("This file is not a valid Excel file.");
            }
        }
    }

    public List<Project> getAllProjects() {
        return this.projectRepository.findAll();
    }

    public Project getProjectById(int projectId) {
        return this.projectRepository.findById(projectId).orElse(null);
    }

    public void deleteProjectById(int projectId) {
        this.projectRepository.deleteById(projectId);
    }

    public void updateProject(int projectId, Project project) {
        this.projectRepository.save(project);
    }

    public void deleteAllProjects() {
        this.projectRepository.deleteAll();
    }

    public void createProject(Project project) {
        this.projectRepository.save(project);
    }

    public void updateOdometer(String licensePlate, Integer odometer, OffsetDateTime date) {
        Optional<Project> optionalProject = this.projectRepository.findByLicensePlate(licensePlate);

        if (optionalProject.isEmpty()) {
            throw new IllegalArgumentException("License plate not found.");
        }

        Project project = optionalProject.get();

        if (odometer <= project.getOdometer()) {
            throw new IllegalArgumentException("Odometer reading must be greater than the current reading.");
        }

        if (!date.isAfter(project.getDate())) {
            throw new IllegalArgumentException("Date must be after the current date.");
        }

        project.setOdometer(odometer);
        project.setDate(date);
        this.projectRepository.save(project);
    }

}
