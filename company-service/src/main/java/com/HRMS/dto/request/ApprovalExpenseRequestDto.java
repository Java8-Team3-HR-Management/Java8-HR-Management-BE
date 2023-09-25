package com.HRMS.dto.request;

import com.HRMS.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ApprovalExpenseRequestDto {

    String id;
    EStatus approvalStatus;
}
