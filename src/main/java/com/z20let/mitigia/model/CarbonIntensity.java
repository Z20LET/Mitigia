package com.z20let.mitigia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CarbonIntensity {
    @Id
    private Integer year;
    private Integer carbonIntensity;
    private String country;
}