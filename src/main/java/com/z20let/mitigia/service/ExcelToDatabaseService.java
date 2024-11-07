
package com.z20let.mitigia.service;

import com.z20let.mitigia.model.CarbonIntensity;
import com.z20let.mitigia.model.EnergyConsumption;
import com.z20let.mitigia.model.Project;
import com.z20let.mitigia.model.Vehicle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class ExcelToDatabaseService {

    public static boolean validateExcelFile(MultipartFile file){
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<Project> getProjectData(InputStream inputStream){

        List<Project> projects = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("project");

            int rowCount = 0;
            for(Row row : sheet){
                if(rowCount == 0){
                    rowCount++;
                    continue;
                }

                Iterator<Cell> cellIterator = row.cellIterator();
                int cellIndex = 0;
                Project project = new Project();
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case 0 -> project.setProjectId((int) cell.getNumericCellValue());
                        case 1 -> project.setLicensePlate(cell.getStringCellValue());
                        case 2 -> project.setVehicleId((long) cell.getNumericCellValue());
                        case 3 -> project.setStartDate(cell.getLocalDateTimeCellValue());
                        case 4 -> project.setEndDate(cell.getLocalDateTimeCellValue());
                        case 5 -> project.setStartOdo((int) cell.getNumericCellValue());
                        case 6 -> project.setEndOdo((int) cell.getNumericCellValue());
                    }
                    cellIndex++;
                }
                projects.add(project);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return projects;
    }


    public static List<Vehicle> getVehicleData(InputStream inputStream) {

        List<Vehicle> vehicles = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("vehicle");

            int rowCount = 0;
            for (Row row : sheet) {
                if (rowCount == 0) {
                    rowCount++;
                    continue;
                }

                Iterator<Cell> cellIterator = row.cellIterator();
                int cellIndex = 0;
                Vehicle vehicle = new Vehicle();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 -> vehicle.setVehicleId((long) cell.getNumericCellValue());
                        case 1 -> vehicle.setVehicleYear((int) cell.getNumericCellValue());
                        case 2 -> vehicle.setVehicleManufacturer(cell.getStringCellValue());
                        case 3 -> vehicle.setVehicleModel(cell.getStringCellValue());
                        case 4 -> vehicle.setVehicleType(cell.getStringCellValue());
                        case 5 -> vehicle.setFuelType(cell.getStringCellValue());
                        case 6 -> vehicle.setEnergyConsumptionWLTP((int) cell.getNumericCellValue());
                        case 7 -> vehicle.setEnergyConsumptionNEDC((int) cell.getNumericCellValue());
                    }
                    cellIndex++;
                }
                vehicles.add(vehicle);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }

    public static List<CarbonIntensity> getCarbonIntensityData(InputStream inputStream) {

        List<CarbonIntensity> carbonIntensities = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("carbon_intensity");

            int rowCount = 0;
            for (Row row : sheet) {
                if (rowCount == 0) {
                    rowCount++;
                    continue;
                }

                Iterator<Cell> cellIterator = row.cellIterator();
                int cellIndex = 0;
                CarbonIntensity carbonIntensity = new CarbonIntensity();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 2 -> carbonIntensity.setYear((int) cell.getNumericCellValue());
                        case 1 -> carbonIntensity.setCarbonIntensity((int) cell.getNumericCellValue());
                        case 0 -> carbonIntensity.setCountry(cell.getStringCellValue());
                    }
                    cellIndex++;
                }
                carbonIntensities.add(carbonIntensity);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return carbonIntensities;
    }

    public static List<EnergyConsumption> getEnergyConsumptionData(InputStream inputStream) {

        List<EnergyConsumption> energyConsumptions = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("energy_consumption");

            int rowCount = 0;
            for (Row row : sheet) {
                if (rowCount == 0) {
                    rowCount++;
                    continue;
                }

                Iterator<Cell> cellIterator = row.cellIterator();
                int cellIndex = 0;
                EnergyConsumption energyConsumption = new EnergyConsumption();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 -> energyConsumption.setId((int) cell.getNumericCellValue());
                        case 1 -> energyConsumption.setDefaultEC((int) cell.getNumericCellValue());
                    }
                    cellIndex++;
                }
                energyConsumptions.add(energyConsumption);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return energyConsumptions;
    }

}
