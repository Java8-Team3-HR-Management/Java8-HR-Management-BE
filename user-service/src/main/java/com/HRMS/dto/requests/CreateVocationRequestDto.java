package com.HRMS.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateVocationRequestDto {
    private String vocationType;
    private String userId;
    private LocalDate startOfVocationDate;
    private LocalDate endOfVocationDate;
}
