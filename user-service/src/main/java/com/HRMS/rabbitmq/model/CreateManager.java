package com.HRMS.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CreateManager {
    Long authId;
    String name;
    String surname;
    String email;
    String companyEmail;
    String companyName;
    String password;
    String title;
    Long salary;
}
