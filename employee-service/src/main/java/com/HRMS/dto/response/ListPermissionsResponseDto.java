package com.HRMS.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Permission;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListPermissionsResponseDto {
    Long employeeId;
    String employeeName;
    List<Permission> permissions;
}
