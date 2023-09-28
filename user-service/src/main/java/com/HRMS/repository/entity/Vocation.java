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
@Document(collection = "vacations")
public class Vocation extends BaseEntity {
    @Id
    private String id;
    private String userId;
    private String vocationType;
    @Builder.Default
    private EStatus vocationStatus= EStatus.PENDING;
    private String companyManagerId;
    private LocalDate startOfVocationDate;
    private LocalDate endOfVocationDate;
    private LocalDate responseOfVocationRequestDate;
    private long vocationDuration;
}

