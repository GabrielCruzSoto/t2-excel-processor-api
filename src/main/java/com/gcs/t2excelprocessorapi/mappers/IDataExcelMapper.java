package com.gcs.t2excelprocessorapi.mappers;

import com.gcs.t2excelprocessorapi.entities.DataExcelEntity;
import org.mapstruct.Mapper;
import org.openapitools.model.ExcelDataGet200Response;
import org.openapitools.model.ExcelDataGet200ResponseContentInner;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface IDataExcelMapper {


    default ExcelDataGet200Response toExcelDataGet200Response(Page<DataExcelEntity> dataExcelPage) {
        ExcelDataGet200Response response = new ExcelDataGet200Response();

        dataExcelPage.getContent().forEach(entity -> {
            ExcelDataGet200ResponseContentInner contentInner = new ExcelDataGet200ResponseContentInner()
                    .nombre(entity.getName())
                    .rut(entity.getRut())
                    .fechaNacimiento(entity.getBirthdate())
                    .sueldo(entity.getSalary());
            response.addContentItem(contentInner);
        });

        response.setTotalPages(dataExcelPage.getTotalPages());
        response.setTotalElements((int) dataExcelPage.getTotalElements());
        response.setSize(dataExcelPage.getSize());
        response.setNumber(dataExcelPage.getNumber());

        return response;
    }
}
