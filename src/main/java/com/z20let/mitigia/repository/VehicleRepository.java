package com.z20let.mitigia.repository;

import com.z20let.mitigia.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
