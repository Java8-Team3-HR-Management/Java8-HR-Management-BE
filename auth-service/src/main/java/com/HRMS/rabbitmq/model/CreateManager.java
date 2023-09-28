package com.HRMS.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CreateManager implements Serializable {
    Long authId;
    String companyId;
    String name;
    String surname;
    String email;
    String companyEmail;
    String companyName;
    String password;
    String title;
    Long salary;
}
