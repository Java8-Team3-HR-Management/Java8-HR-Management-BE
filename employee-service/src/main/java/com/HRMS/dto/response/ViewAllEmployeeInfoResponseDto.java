package com.HRMS.dto.response;

import com.HRMS.repository.enums.EContractStatement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ViewAllEmployeeInfoResponseDto {

    private String id;
    private Long authId;
    private String companyId;
    private String nameSurname;
    private String email;
    private String companyName;
    private String companyEmail;
    private String birthPlace;
    private LocalDate birthDate;
    private String department;
    private String title;
    private String location;
    private String phone;
    private LocalDate membershipDate;
    private Long salary;
    private EContractStatement contractStatement;

}
