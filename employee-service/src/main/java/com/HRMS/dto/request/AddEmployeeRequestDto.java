package com.HRMS.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddEmployeeRequestDto {
    String nameSurname;
    String email;
    String password;
    String department;
    String title;
    String location;
    Long salary;
}
