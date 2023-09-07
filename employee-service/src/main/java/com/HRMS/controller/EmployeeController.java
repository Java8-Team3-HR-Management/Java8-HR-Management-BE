package com.HRMS.controller;

import com.HRMS.dto.request.AddEmployeeRequestDto;
import com.HRMS.dto.request.ListPermissionsRequestDto;
import com.HRMS.dto.response.AddEmployeeResponseDto;
import com.HRMS.dto.response.ListPermissionsResponseDto;
import com.HRMS.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import static com.HRMS.constants.RestApiList.*;

@RestController
@RequestMapping(EMPLOYEE)
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService service;
    @PostMapping(ADDEMPLOYEE)
    public ResponseEntity<AddEmployeeResponseDto> addEmployee(@RequestBody @Valid AddEmployeeRequestDto dto) {
        Boolean isAddedSuccessfully = service.addEmployee(dto);
        if (isAddedSuccessfully) {
            return ResponseEntity.ok(AddEmployeeResponseDto.builder()
                            .status(100)
                            .result("Successfull")
                    .build());
        }
        return ResponseEntity.badRequest().body(AddEmployeeResponseDto.builder()
                .status(200)
                .result("Please check the details you entered")
                .build());

    }


    @GetMapping(PERMISSIONEMPLOYEE)
    public ListPermissionsResponseDto listPermissionsByEmployeeId(@PathVariable Long employeeId) {
        ListPermissionsRequestDto request = new ListPermissionsRequestDto();
        request.setEmployeeId(employeeId);
        return service.listPermissionsByEmployeeId(request);
    }





}
