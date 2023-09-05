package com.HRMS.controller;

import com.HRMS.dto.request.AddEmployeeRequestDto;
import com.HRMS.dto.response.AddEmployeeResponseDto;
import com.HRMS.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService service;
    @PostMapping("/addEmployee")
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
}
