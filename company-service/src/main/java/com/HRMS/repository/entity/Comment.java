package com.HRMS.repository.entity;

import com.HRMS.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document(collection = "comments")
public class Comment extends BaseEntity {
    @Id
    String id;
    String companyName;
    String companyId;
    String commentSubject;
    String commentContent;
    String employeeId;
    String employeeName;
    Double rate;
    @Builder.Default
    EStatus status = EStatus.PENDING;
}
