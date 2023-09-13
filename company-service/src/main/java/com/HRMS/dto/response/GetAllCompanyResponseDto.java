package com.HRMS.dto.response;

import com.HRMS.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static com.HRMS.repository.enums.EStatus.PENDING;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GetAllCompanyResponseDto {
    String companyName;
    String companyAddress;
    String companyPhone;
    String companyEmail;
    String companyWebsite;
    Long taxNumber;
    EStatus status;
}
