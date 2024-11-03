package com.z20let.mitigia.repository;

import com.z20let.mitigia.model.OdometerReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdometerRepository extends JpaRepository<OdometerReading, Integer> {
}
