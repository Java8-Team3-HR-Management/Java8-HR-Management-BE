package com.HRMS.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ViewAllEmployeeInfoRequestDto {
    private String companyId;
    private String department;
}
