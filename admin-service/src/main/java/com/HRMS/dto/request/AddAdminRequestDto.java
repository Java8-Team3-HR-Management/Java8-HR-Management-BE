package com.HRMS.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddAdminRequestDto {
    String companyid;
    String nameSurname;
    String email;
    String companyEmail;
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
