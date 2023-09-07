package com.HRMS.controller;

import com.HRMS.dto.request.AddAdminRequestDto;
import com.HRMS.dto.response.AddAdminResponseDto;
import com.HRMS.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.HRMS.constants.RestApis.*;
@RestController
@RequestMapping(ADMIN)
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping(ADDADMIN)
    @CrossOrigin("*")
    public ResponseEntity<AddAdminResponseDto> addAdmin(@RequestBody @Valid AddAdminRequestDto dto){
        Boolean isAddedSuccesfully = adminService.addAdmin(dto);
        if(isAddedSuccesfully)
            return ResponseEntity.ok(AddAdminResponseDto.builder()
                    .status(100)
                    .result("Successfull")
                    .build());
        return ResponseEntity.badRequest().body(
                AddAdminResponseDto.builder()
                        .status(200)
                        .result("Please check the details you entered!")
                        .build()
        );
    }



}


