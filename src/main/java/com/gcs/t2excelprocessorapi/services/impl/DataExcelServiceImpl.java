package com.gcs.t2excelprocessorapi.services.impl;

import com.gcs.t2excelprocessorapi.entities.DataExcelEntity;
import com.gcs.t2excelprocessorapi.exceptions.ExcelUnprocessableEntityException;
import com.gcs.t2excelprocessorapi.mappers.IDataExcelMapper;
import com.gcs.t2excelprocessorapi.repositories.IDataExcelRepository;
import com.gcs.t2excelprocessorapi.services.IDataExcelService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openapitools.model.ExcelDataGet200Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataExcelServiceImpl implements IDataExcelService {

    private final IDataExcelRepository excelDataRepository;
    private final IDataExcelMapper dataExcelMapper;

    @Override
    @Transactional
    public void processExcelFile(MultipartFile file) {

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            List<DataExcelEntity> dataList = new ArrayList<>();
            int recordsProcessed = 0;

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row
                DataExcelEntity data = processRow(row);
                dataList.add(data);
                recordsProcessed++;

            }

            excelDataRepository.saveAll(dataList);
        } catch (IOException e) {
            throw new ExcelUnprocessableEntityException("Failed to process Excel file");
        }
    }

    @Override
    public ExcelDataGet200Response findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DataExcelEntity> pageDataExcel =this.excelDataRepository.findAll(pageable);
        ExcelDataGet200Response excelDataGet200Response = this.dataExcelMapper.toExcelDataGet200Response(pageDataExcel);
        return excelDataGet200Response;
    }

    private DataExcelEntity processRow(Row row) {
        DataExcelEntity data = new DataExcelEntity();
        data.setRut(getCellValueAsString(row.getCell(0)));
        data.setName(getCellValueAsString(row.getCell(1)));
        data.setBirthdate(getCellValueAsLocalDate(row.getCell(2)));
        data.setSalary(getCellValueAsBigDecimal(row.getCell(3)));
        return data;
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) return null;
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            default -> null;
        };
    }

    private LocalDate getCellValueAsLocalDate(Cell cell) {
        if (cell == null) return null;
        try {

            return LocalDate.of(1899, 12, 30).plus((long) cell.getNumericCellValue(), ChronoUnit.DAYS);
        } catch (IllegalStateException e) {
            return null;
        }
    }
    private BigDecimal getCellValueAsBigDecimal(Cell cell) {
        if (cell == null) return null;
        try {
            return BigDecimal.valueOf(cell.getNumericCellValue());
        } catch (IllegalStateException e) {
            return null;
        }
    }
}
