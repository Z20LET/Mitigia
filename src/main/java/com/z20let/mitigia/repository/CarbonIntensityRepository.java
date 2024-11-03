package com.z20let.mitigia.repository;

import com.z20let.mitigia.model.CarbonIntensity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarbonIntensityRepository extends JpaRepository<CarbonIntensity, Integer> {

    Integer findCarbonIntensityBy(Integer year);
}
