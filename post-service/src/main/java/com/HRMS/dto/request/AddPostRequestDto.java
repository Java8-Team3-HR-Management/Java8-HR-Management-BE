package com.HRMS.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AddPostRequestDto {
    private String companyName;
    private String companyId;
    private String postSubject;
    private String postContent;
    private String employeeId;
    private String employeeName;

}
