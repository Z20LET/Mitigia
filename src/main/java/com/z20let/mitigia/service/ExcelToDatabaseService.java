package com.z20let.mitigia.service;

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

    public static List<Project> getDataFromExcelFile(InputStream inputStream){

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
                Vehicle vehicle = new Vehicle();
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){ case 0 -> project.setProjectId((int) cell.getNumericCellValue());
                                        case 1 -> vehicle.setLicensePlate(cell.getStringCellValue());
                                        case 2 -> vehicle.setVehicleId((long) cell.getNumericCellValue());
                                        case 3 -> project.setStartDate(cell.getLocalDateTimeCellValue().toLocalDate());
                                        case 4 -> project.setEndDate(cell.getLocalDateTimeCellValue().toLocalDate());
                                        case 5 -> project.setStartMileage((int) cell.getNumericCellValue());
                                        case 6 -> project.setEndMileage((int) cell.getNumericCellValue());
                    }
                    cellIndex++;
                }
                project.setVehicle(vehicle);
                projects.add(project);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return projects;
    }





}
