package com.HRMS.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AddCompanyRequestDto {
    @Size(min = 8,max=64)
    String companyName;
    @Size(min = 8,max=200)
    String companyAddress;
    @Size(min = 11,max=11)
    String companyPhone;
    @Email
    String companyEmail;
    @Size(min = 10,max=64)
    String companyWebsite;

    Long taxNumber;
}
