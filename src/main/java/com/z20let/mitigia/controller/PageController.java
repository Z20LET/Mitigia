package com.z20let.mitigia.controller;

import com.z20let.mitigia.model.Project;
import com.z20let.mitigia.service.OdometerReadingService;
import com.z20let.mitigia.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PageController {
    private final ProjectService projectService;
    private final OdometerReadingService odometerService;

    public PageController(ProjectService projectService, OdometerReadingService odometerService) {
        this.projectService = projectService;
        this.odometerService = odometerService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to my Java Spring Boot application!");
        return "index";
    }

    @GetMapping("/list-projects")
    public String listProjects(Model model) {
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "projects";
    }

    @GetMapping("/odometer")
    public String odometer() {
        return "odometer";
    }

    @PostMapping("/update-odometer")
    public String updateOdometer(@RequestParam("licensePlate") String licensePlate,
                                 @RequestParam("odometer") String odometer,
                                 @RequestParam("date") String date,
                                 Model model) {
        String message = odometerService.updateOdometer(licensePlate, odometer, date);
        model.addAttribute("responseMessage", message);
        return "odometer";
    }

    @GetMapping("piechart")
    public String pieChart() {
        return "piechart";
    }
}