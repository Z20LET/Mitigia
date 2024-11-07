package com.z20let.mitigia.controller;

import com.z20let.mitigia.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@AllArgsConstructor
@RequestMapping("/api/upload-excel")
public class ExcelToDatabaseController {


    private VehicleService vehicleService;
    private ProjectService projectService;
    private CarbonIntensityService carbonIntensityService;
    private EnergyConsumptionService energyConsumptionService;

    @PostMapping
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        if (!ExcelToDatabaseService.validateExcelFile(file)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid file type. Please upload an Excel file with .xlsx format.");
        }

        try {
            vehicleService.saveVehicles(file);
            projectService.saveProjects(file);
            carbonIntensityService.saveCarbonIntensities(file);
            energyConsumptionService.saveEnergyConsumptions(file);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("File uploaded and data saved successfully.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the file: " + e.getMessage());
        }
    }

}
