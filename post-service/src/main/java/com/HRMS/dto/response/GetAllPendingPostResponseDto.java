package com.HRMS.dto.response;

import com.HRMS.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetAllPendingPostResponseDto {
    String companyName;
    String postSubject;
    String postContent;
    String employeeName;
    EStatus status;
}
