package com.HRMS.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateVocationResponseDto {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private Long companyId;
}
