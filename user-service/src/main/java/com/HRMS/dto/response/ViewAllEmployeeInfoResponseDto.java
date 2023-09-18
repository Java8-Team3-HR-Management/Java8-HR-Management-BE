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

    private String name;
    private String surname;
    private String email;
    private String companyName;
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
