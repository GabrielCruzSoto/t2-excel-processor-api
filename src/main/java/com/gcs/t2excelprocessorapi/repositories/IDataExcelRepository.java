package com.gcs.t2excelprocessorapi.repositories;

import com.gcs.t2excelprocessorapi.entities.DataExcelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDataExcelRepository extends JpaRepository<DataExcelEntity, Long> {
}
