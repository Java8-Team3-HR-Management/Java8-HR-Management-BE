package com.HRMS.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CreateEmployee implements Serializable {
    Long companyId;
    Long authId;
    String name;
    String surname;
    String email;
    String companyEmail;
    String companyName;
    String birthPlace;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate birthDate;
    String department;
    String title;
    String location;
    String phone;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate membershipDate;
    Long salary;

}
