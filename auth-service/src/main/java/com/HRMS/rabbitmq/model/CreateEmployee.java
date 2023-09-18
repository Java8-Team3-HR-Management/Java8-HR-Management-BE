package com.HRMS.rabbitmq.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CreateEmployee implements Serializable {
    String companyId;
    Long authId;
    String name;
    String surname;
    String email;
    String companyEmail;
    String companyName;
    String birthPlace;
    LocalDate birthDate;
    String department;
    String title;
    String location;
    String phone;
    LocalDate membershipDate;
    Long salary;

}
