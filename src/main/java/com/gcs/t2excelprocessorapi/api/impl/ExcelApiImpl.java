package com.gcs.t2excelprocessorapi.api.impl;



import com.gcs.t2excelprocessorapi.services.IDataExcelService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.ExcelApi;
import org.openapitools.model.ExcelDataGet200Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ExcelApiImpl implements ExcelApi {
    private final IDataExcelService dataExcelService;

    @Override
    public ResponseEntity<ExcelDataGet200Response> excelDataGet(Integer page, Integer size){
        ExcelDataGet200Response excelDataGet200Response = this.dataExcelService.findAll(page,size);
        return ResponseEntity.ok(excelDataGet200Response);
    }

    @Override
    public ResponseEntity<Void> excelUploadPost(MultipartFile file){
        this.dataExcelService.processExcelFile(file);
        return ResponseEntity.ok(null);
    }

}
