package com.HRMS.controller;

import com.HRMS.dto.request.AddManagerRequestDto;
import com.HRMS.dto.response.AddManagerResponseDto;
import com.HRMS.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.HRMS.constants.RestApiList.*;


@RestController
@RequestMapping(MANAGER)
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService service;

    @PostMapping(ADDMANAGER)
    public ResponseEntity<AddManagerResponseDto> addManager(AddManagerRequestDto dto) {
        Boolean check = service.addManager(dto);
        if (check) {
            return ResponseEntity.ok(AddManagerResponseDto.builder().result("Manager added successfully").build());
        }
        return ResponseEntity.badRequest().body(AddManagerResponseDto.builder().result("Please check the details you entered").build());
    }
}
