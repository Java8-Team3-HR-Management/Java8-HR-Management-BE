package com.HRMS.dto.requests;

import com.HRMS.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateVacationRequestDto {
    private EStatus vocationStatus;
    private String userId;
}
