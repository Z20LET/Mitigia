package com.z20let.mitigia.controller;

import com.z20let.mitigia.model.ProjectDTO;
import com.z20let.mitigia.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/projects")
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

    @PutMapping("update-project-by-id")
    public ResponseEntity<?> updateProject(@RequestBody ProjectDTO project){
        projectService.updateProject(project);
        return ResponseEntity.ok(Map.of("Message", "Project updated successfully"));
    }

    @GetMapping("all-projects")
    public ResponseEntity<?> getAllProjects(){
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("get-project-by-id")
    public ResponseEntity<?> getProjectById(@RequestParam("id") Integer projectId){
        return ResponseEntity.ok(projectService.getProjectById(projectId));
    }

    @DeleteMapping("delete-project-by-id")
    public ResponseEntity<?> deleteProject(@RequestParam("id") Integer projectId){
        return ResponseEntity.ok(projectService.deleteProjectById(projectId));
    }

    @DeleteMapping("delete-all")
    public ResponseEntity<?> deleteAllProjects(){
        projectService.deleteAllProjects();
        return ResponseEntity.ok(Map.of("Message", "All projects deleted successfully"));
    }
}
