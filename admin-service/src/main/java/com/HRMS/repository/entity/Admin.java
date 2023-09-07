package com.HRMS.repository.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class Admin extends BaseEntity {
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

}

