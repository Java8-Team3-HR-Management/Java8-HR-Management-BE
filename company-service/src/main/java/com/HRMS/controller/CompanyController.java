package com.HRMS.controller;

import com.HRMS.dto.request.AddCompanyRequestDto;
import com.HRMS.dto.request.UpdateCompanyRequestDto;
import com.HRMS.dto.response.AddCompanyResponseDto;
import com.HRMS.dto.response.CompanyAverageRateResponseDto;
import com.HRMS.dto.response.GetAllCompanyResponseDto;
import com.HRMS.dto.response.UpdateCompanyResponseDto;
import com.HRMS.services.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.HRMS.constants.RestApiList.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMPANY)
@CrossOrigin("*")
public class CompanyController {
    private final CompanyService service;

    @PostMapping("/add-company")
    public ResponseEntity<AddCompanyResponseDto> addCompany(@RequestBody @Valid AddCompanyRequestDto dto){
        Boolean check = service.addCompany(dto);
        if (check){
            return ResponseEntity.ok(AddCompanyResponseDto.builder()
                            .status(100)
                            .result("Company added successfully")
                    .build());
        }
        return ResponseEntity.badRequest().body(AddCompanyResponseDto.builder()
                .status(200)
                .result("Please check the details you entered")
                .build());
    }
    @GetMapping("/get-all-company")
    public ResponseEntity<Optional<List<GetAllCompanyResponseDto>>> getAllCompany(){
        List<GetAllCompanyResponseDto> list = service.getAllCompanies();
        return ResponseEntity.ok(Optional.of(list));
    }
    @GetMapping("/get-all-company-waiting-for-approval")
    public ResponseEntity<Optional<List<GetAllCompanyResponseDto>>> getAllCompanyApproval(){
        List<GetAllCompanyResponseDto> list = service.getAllCompaniesWaitingForApproval();
        return ResponseEntity.ok(Optional.of(list));
    }
    @PutMapping("/update-company")
    public ResponseEntity<UpdateCompanyResponseDto> updateCompany(@RequestBody UpdateCompanyRequestDto dto){
        Boolean check = service.updateCompany(dto);
        if (check){
            return ResponseEntity.ok(UpdateCompanyResponseDto.builder()
                    .status(100)
                    .result("Company updated successfully")
                    .build());
        }
        return ResponseEntity.badRequest().body(UpdateCompanyResponseDto.builder()
                .status(200)
                .result("Please check the details you entered")
                .build());
    }
    @GetMapping("/get-company-by-name/{companyName}")
    public ResponseEntity<GetAllCompanyResponseDto> getCompanyByName(@PathVariable String companyName){
        return ResponseEntity.ok(service.getCompanyByName(companyName));
    }
    @GetMapping("/get-company-avg-rate/{companyId}")
    public ResponseEntity<CompanyAverageRateResponseDto> getCompanyAvgRate(@PathVariable String companyId){
        return ResponseEntity.ok(service.getCompanyAverageRate(companyId));
    }
}
