package com.HRMS.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddCommentRequestDto {
     String companyName;
     String commentSubject;
     String commentContent;
     Double rate;
     String authId;
     String employeeName;
}
