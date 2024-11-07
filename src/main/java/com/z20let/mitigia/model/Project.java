package com.z20let.mitigia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    private int projectId;
    private String licensePlate;
    private Long vehicleId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int startOdo;
    private int endOdo;
    private int mileage;
    private double carbonEmission;
}
