package com.z20let.mitigia.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ProjectDTO {
    private int projectId;
    private String licensePlate;
    private long vehicleId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int startOdometer;
    private int endOdometer;
}
