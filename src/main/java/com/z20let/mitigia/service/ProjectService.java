package com.z20let.mitigia.service;

import com.z20let.mitigia.model.Project;
import com.z20let.mitigia.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

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

    public void deleteAllProjects() {
        this.projectRepository.deleteAll();
    }

    public void updateProject(Project project) {
        this.projectRepository.save(project);
    }
}
