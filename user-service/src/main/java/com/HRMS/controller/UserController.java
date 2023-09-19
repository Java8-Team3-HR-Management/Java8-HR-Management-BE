package com.HRMS.controller;

import com.HRMS.dto.requests.UpdateEmployeeRequestDto;
import com.HRMS.dto.requests.ViewAllEmployeeInfoRequestDto;
import com.HRMS.dto.response.ViewAllEmployeeInfoResponseDto;
import com.HRMS.repository.entity.User;
import com.HRMS.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.HRMS.constants.RestApiList.*;



@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserService service;

    @GetMapping("/findAllEmployee/{companyId}")
    public ResponseEntity <List<User>> findAllEmployees(@PathVariable String companyId) {
        return ResponseEntity.ok(service.findAllEmployees(companyId));}

    @GetMapping("/getEmployeeByAuthId/{authId}")
    public ResponseEntity<User> getEmployeeById(@PathVariable Long authId) {
        User employee =  service.getEmployeeByAuthId(authId);
        return ResponseEntity.ok(employee);
    }
    @PutMapping("/updateEmployee")
    public ResponseEntity<Boolean> updateEmployee(@RequestBody @Valid UpdateEmployeeRequestDto dto) {
        return ResponseEntity.ok(service.updateEmployee(dto));
    }
    @GetMapping("/viewAllEmployeeInfo")
    public ResponseEntity<Optional<ViewAllEmployeeInfoResponseDto>> viewAllEmployeeInfo(@RequestBody @Valid ViewAllEmployeeInfoRequestDto requestDto) {
        Optional<ViewAllEmployeeInfoResponseDto> employee = service.viewAllEmployeeInfo(requestDto);
        return ResponseEntity.ok(employee);}
}
