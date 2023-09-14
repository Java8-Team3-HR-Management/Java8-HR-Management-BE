package com.HRMS.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AddManagerRequestDto {
    Long companyId;
    String name;
    String surname;
    String email;
    String companyEmail;
    String password;
    String title;
    Long salary;
}
