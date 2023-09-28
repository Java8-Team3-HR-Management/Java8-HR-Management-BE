package com.HRMS.repository.entity;

import com.HRMS.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document
public class Debt extends BaseEntity{
    @Id
    private String id;
    private String userId;
    private double salary;
    private LocalDate lastAdvanceDate;
    @Builder.Default
    private EStatus eStatus = EStatus.PENDING;


}
