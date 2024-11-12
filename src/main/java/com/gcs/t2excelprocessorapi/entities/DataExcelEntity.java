package com.gcs.t2excelprocessorapi.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "TBL_EXCEL_DATA")
public class DataExcelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String rut;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false)
    private BigDecimal salary;

    @CreationTimestamp
    @Column(name = "created_on", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdOn;

    @PrePersist
    protected void onCreate() {
        if (createdOn == null) {
            createdOn = new Timestamp(System.currentTimeMillis());
        }
    }
}
