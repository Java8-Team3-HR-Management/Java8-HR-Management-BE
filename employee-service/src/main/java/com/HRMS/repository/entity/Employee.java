package com.HRMS.repository.entity;

import com.HRMS.repository.enums.EContractStatement;
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
@Document(collection = "employees")
public class Employee extends BaseEntity {
    @Id
    String id;
    Long authid;
    String companyid;
    String nameSurname;
    String email;
    String companyEmail;
    String password;
    String birthPlace;
    LocalDate birthDate;
    String department;
    String title;
    String location;
    String phone;
    LocalDate membershipDate;
    Long salary;
    @Builder.Default
    EContractStatement contractStatement=EContractStatement.ACTIVE;
}
