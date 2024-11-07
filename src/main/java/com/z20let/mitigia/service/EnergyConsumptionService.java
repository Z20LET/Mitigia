package com.z20let.mitigia.service;

import com.z20let.mitigia.model.EnergyConsumption;
import com.z20let.mitigia.repository.EnergyConsumptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
@AllArgsConstructor
public class EnergyConsumptionService {

    private final EnergyConsumptionRepository energyConsumptionRepository;

    public void saveEnergyConsumptions(MultipartFile file) {
        if(ExcelToDatabaseService.validateExcelFile(file)) {
            try {
                List<EnergyConsumption> energyConsumptions = ExcelToDatabaseService.getEnergyConsumptionData(file.getInputStream());
                energyConsumptionRepository.saveAll(energyConsumptions);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
