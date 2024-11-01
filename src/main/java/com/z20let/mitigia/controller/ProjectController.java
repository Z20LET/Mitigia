package com.z20let.mitigia.controller;

import com.z20let.mitigia.model.Project;
import com.z20let.mitigia.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private ProjectService projectService;

    @PostMapping("/upload-project")
    public ResponseEntity<?> UploadProject(@RequestParam("file") MultipartFile file) {
        this.projectService.saveProject(file);
        return ResponseEntity.ok(Map.of("Message", "Project uploaded successfully"));
    }

    @PutMapping("/update-project")
    public ResponseEntity<?> updateProject(@RequestBody Project project) {
        this.projectService.updateProject(project);
        return ResponseEntity.ok(Map.of("Message", "Project updated successfully"));
    }

    @GetMapping("/all-projects")
    public ResponseEntity<List<Project>> getAllProjects() {
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<Project> getProjectById(@RequestParam int projectId) {
        return new ResponseEntity<>(projectService.getProjectById(projectId), HttpStatus.FOUND);
    }

    @DeleteMapping("/delete-project-by-id")
    public ResponseEntity<?> deleteProjectById(@RequestParam int projectId) {
        projectService.deleteProjectById(projectId);
        return ResponseEntity.ok(Map.of("Message", "Project deleted successfully"));
    }

    @DeleteMapping("/delete-all-projects")
    public ResponseEntity<?> deleteAllProjects() {
        projectService.deleteAllProjects();
        return ResponseEntity.ok(Map.of("Message", "All projects deleted successfully"));
    }


}
