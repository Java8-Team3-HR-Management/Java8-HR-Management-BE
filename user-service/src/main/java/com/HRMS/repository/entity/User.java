package com.HRMS.repository.entity;

import com.HRMS.repository.enums.EContractStatement;
import com.HRMS.repository.enums.ERole;
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
@Document(collection = "users")
public class User extends BaseEntity
{
    @Id
    String id;
    Long authid;
    String name;
    String surname;
    Long companyId;
    String email;
    String companyName;
    String companyEmail;
    String birthPlace;
    LocalDate birthDate;
    String department;
    String title;
    String location;
    String phone;
    LocalDate membershipDate;
    Long salary;
    EStatus status;
    ERole role;
    EContractStatement contractStatement;

}
