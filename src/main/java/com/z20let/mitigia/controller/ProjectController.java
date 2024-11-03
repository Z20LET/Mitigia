package com.z20let.mitigia.controller;

import com.z20let.mitigia.model.Project;
import com.z20let.mitigia.model.ProjectDTO;
import com.z20let.mitigia.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/projects")
public class ProjectController {

    private ProjectService projectService;

    @PostMapping("create-project")
    public ResponseEntity<?> createProject(@RequestBody ProjectDTO project){
        projectService.createProject(project);
        return ResponseEntity.ok(Map.of("Message", "Project created successfully"));
    }

    @PostMapping("upload-project")
    public ResponseEntity<?> uploadProject(@RequestParam("file") MultipartFile file){
        projectService.saveProject(file);
        return ResponseEntity.ok(Map.of("Message", "Project uploaded successfully"));
    }
}
