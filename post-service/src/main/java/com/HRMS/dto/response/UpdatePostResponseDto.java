package com.HRMS.dto.response;

import com.HRMS.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UpdatePostResponseDto {

    private String id;
    private String companyName;
    private String companyId;
    private String postSubject;
    private String postContent;
    private String employeeId;
    private String employeeName;
    private EStatus status;
}
