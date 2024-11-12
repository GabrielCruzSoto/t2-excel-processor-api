package com.gcs.t2excelprocessorapi.services;

import org.openapitools.model.ExcelDataGet200Response;
import org.springframework.web.multipart.MultipartFile;

public interface IDataExcelService {

    void processExcelFile(MultipartFile file);
    ExcelDataGet200Response findAll(Integer page, Integer size);
}
