package com.z20let.mitigia.repository;

import com.z20let.mitigia.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Optional<Project> findByLicensePlate(String licensePlate);
}
