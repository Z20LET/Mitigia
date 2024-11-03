package com.z20let.mitigia.controller;

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

    @PostMapping("upload-project")
    public ResponseEntity<?> uploadProject(@RequestParam("file") MultipartFile file){
        projectService.saveProject(file);
        return ResponseEntity.ok(Map.of("Message", "Project uploaded successfully"));
    }
}
