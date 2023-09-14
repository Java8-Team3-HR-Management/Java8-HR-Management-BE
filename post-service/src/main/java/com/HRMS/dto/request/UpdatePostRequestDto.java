package com.HRMS.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UpdatePostRequestDto {
    private String postId;
    private String companyName;
    private String companyId;
    private String postSubject;
    private String postContent;
    private String employeeId;
    private String employeeName;


}
