package com.z20let.mitigia.service;

import com.z20let.mitigia.model.Project;
import com.z20let.mitigia.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;


    public void saveProjects(MultipartFile file) {
        if (ExcelToDatabaseService.validateExcelFile(file)) {
            try {
                List<Project> projects = com.z20let.mitigia.service.ExcelToDatabaseService.getProjectData(file.getInputStream());
                for (Project project : projects) {
                    projectRepository.saveAll(projects);
                }
            } catch (IOException e) {
                throw new IllegalArgumentException("This is not a valid excel file");
            }
        }
    }

}
