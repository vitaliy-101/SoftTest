package org.example.softproject.service;

import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for reading numbers from Excel files.
 */
@Service
public class ExcelFileService {

    /**
     * Reads numbers from the first column of the first sheet in an Excel file.
     *
     * @param filePath path to the Excel file
     * @return list of numbers from the first column
     * @throws IOException if there's an error reading the file
     */
    public List<Integer> readNumbersFromExcel(String filePath) throws IOException {
        if (!filePath.toLowerCase().endsWith(".xlsx")) {
            throw new IllegalArgumentException("Only .xlsx files are supported");
        }

        List<Integer> numbers = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    numbers.add((int) cell.getNumericCellValue());
                }
            }
        } catch (OfficeXmlFileException e) {
            throw new IOException("The file is not in Excel XLSX format", e);
        }

        return numbers;
    }
}