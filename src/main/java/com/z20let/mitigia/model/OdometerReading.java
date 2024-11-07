package com.z20let.mitigia.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OdometerReading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    private Integer mileage;
    private String licensePlate;

    public OdometerReading(String licensePlate, int odometer, LocalDate date) {
        this.date = date;
        this.mileage = odometer;
        this.licensePlate = licensePlate;
    }
}
