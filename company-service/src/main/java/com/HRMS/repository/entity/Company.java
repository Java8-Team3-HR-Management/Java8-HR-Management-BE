package com.HRMS.repository.entity;

import com.HRMS.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.HRMS.repository.enums.EStatus.PENDING;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document(collection = "companies")
public class Company extends BaseEntity {
    @Id
    String id;
    String companyName;
    String companyAddress;
    String companyPhone;
    String companyEmail;
    String companyWebsite;
    Long taxNumber;
    Double totalRate;
    @Builder.Default
    EStatus status = PENDING;
}
