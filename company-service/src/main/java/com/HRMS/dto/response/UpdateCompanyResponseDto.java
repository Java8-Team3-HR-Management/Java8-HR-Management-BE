package com.HRMS.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateCompanyResponseDto {
    /**
     * Status code:
     * 100: Successfull
     * 200: Error
     */

    int status;
    String result;
}
