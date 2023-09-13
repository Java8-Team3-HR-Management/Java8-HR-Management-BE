package com.HRMS.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateEmployeeResponseDto {

    private String employeeId;
    private String nameSurname;
    private String email;
    private String phoneNumber;



}

