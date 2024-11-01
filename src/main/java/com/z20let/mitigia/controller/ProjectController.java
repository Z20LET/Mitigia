package com.z20let.mitigia.controller;

import com.z20let.mitigia.model.Project;
import com.z20let.mitigia.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/upload-project")
    public ResponseEntity<?> UploadProject(@RequestParam("file") MultipartFile file) {
        this.projectService.saveProject(file);
        return ResponseEntity.ok(Map.of("Message", "Project uploaded successfully"));
    }

    @PostMapping("/create-project")
    public ResponseEntity<?> createProject(@RequestBody Project project) {
        this.projectService.createProject(project);
        return ResponseEntity.ok(Map.of("Message", "Project updated successfully"));
    }

    @PutMapping("/update-project-by-id")
    public ResponseEntity<?> updateProject(@RequestParam("id") Integer projectId, @RequestBody Project project) {
        this.projectService.updateProject(projectId, project);
        return ResponseEntity.ok(Map.of("Message", "Project updated successfully"));
    }

    @GetMapping("/all-projects")
    public ResponseEntity<List<Project>> getAllProjects() {
        return new ResponseEntity<>(this.projectService.getAllProjects(), HttpStatus.FOUND);
    }

    @GetMapping("/project-by-id")
    public ResponseEntity<Project> getProjectById(@RequestParam("id") Integer projectId) {
        return new ResponseEntity<>(projectService.getProjectById(projectId), HttpStatus.FOUND);
    }

    @DeleteMapping("/delete-project-by-id")
    public ResponseEntity<?> deleteProjectById(@RequestParam("id") Integer projectId) {
        projectService.deleteProjectById(projectId);
        return ResponseEntity.ok(Map.of("Message", "Project deleted successfully"));
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<?> deleteAllProjects() {
        projectService.deleteAllProjects();
        return ResponseEntity.ok(Map.of("Message", "All projects deleted successfully"));
    }


}
