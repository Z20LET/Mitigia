package com.z20let.mitigia.service;

import com.z20let.mitigia.model.Project;
import com.z20let.mitigia.model.Vehicle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ProjectUploadService {

    public static boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<Project> getProjectDataFromExcel(InputStream inputStream){
        List<Project> projects = new ArrayList<>();
;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("project");

            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Project project = new Project();
                Vehicle vehicle = new Vehicle();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 -> project.setProjectId((int) cell.getNumericCellValue());
                        case 1 -> project.setLicensePlate(cell.getStringCellValue());
                        case 2 -> vehicle.setVehicleId((long) cell.getNumericCellValue());
                        case 3 -> project.setStartDate(cell.getDateCellValue());
                        case 4 -> project.setEndDate(cell.getDateCellValue());
                        case 5 ->project.setStartOdometer((int) cell.getNumericCellValue());
                        case 6 -> project.setEndOdometer((int) cell.getNumericCellValue());
                        default -> {
                        }
                    }
                    cellIndex++;
                }
                projects.add(project);
            }

        } catch (IOException e) {
            e.getStackTrace();
        }

        return projects;
    }
}
