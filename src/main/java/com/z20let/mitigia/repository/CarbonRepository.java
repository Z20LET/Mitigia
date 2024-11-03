package com.z20let.mitigia.repository;

import com.z20let.mitigia.model.CarbonIntensity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarbonRepository {
    interface OdometerRepository extends JpaRepository<CarbonIntensity, Integer> {
    }
}