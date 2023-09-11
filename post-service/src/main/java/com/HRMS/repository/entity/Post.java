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
@Document(collection = "posts")
public class Post {
    @Id
    String id;
    String companyName;
    String companyId;
    String postSubject;
    String postContent;
    String employeeId;
    String employeeName;
    @Builder.Default
    EStatus status=EStatus.PENDING;
}
