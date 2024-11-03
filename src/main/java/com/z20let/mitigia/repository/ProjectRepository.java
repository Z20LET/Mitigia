package com.z20let.mitigia.repository;

import com.z20let.mitigia.model.Project;
import com.z20let.mitigia.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Optional<Project> findByVehicle(Vehicle vehicle);
}
