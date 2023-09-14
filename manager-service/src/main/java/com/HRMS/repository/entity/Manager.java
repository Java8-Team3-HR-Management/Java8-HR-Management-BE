package com.HRMS.repository.entity;


import com.HRMS.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Manager extends BaseEntity {
    @Id
    String id;
    Long companyId;
    String name;
    String surname;
    String email;
    String companyEmail;
    String password;
    String title;
    Long salary;
    EStatus status;

}
