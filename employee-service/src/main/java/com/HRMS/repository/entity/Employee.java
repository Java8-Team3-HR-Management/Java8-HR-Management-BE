package com.HRMS.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "employees")
public class Employee {
    @Id
    String id;
    String nameSurname;
    String email;
    String password;
    String department;
    String title;
    String location;
    Long salary;
    @Builder.Default
    Boolean isActive=true;
}
