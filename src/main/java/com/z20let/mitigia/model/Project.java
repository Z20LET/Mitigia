package com.z20let.mitigia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project {
    @Id
    private Integer projectId;
    private String licensePlate;

    @ManyToOne
    @JoinColumn(name = "vehicleId", referencedColumnName = "vehicleId")
    private Vehicle vehicle;
    private Date startDate;
    private Date endDate;
    private Integer startOdometer;
    private Integer endOdometer;
}
