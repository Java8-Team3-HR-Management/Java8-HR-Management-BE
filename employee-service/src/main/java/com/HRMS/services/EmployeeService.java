package com.HRMS.services;

import com.HRMS.dto.request.AddEmployeeRequestDto;
import com.HRMS.mapper.IEmployeeMapper;
import com.HRMS.repository.IEmployeeRepository;
import com.HRMS.repository.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final IEmployeeRepository repository;

    public Boolean addEmployee(AddEmployeeRequestDto dto){
        repository.findOptionalByEmail(dto.getEmail()).ifPresent(employee -> {

            throw new RuntimeException("Employee with this email already exists");
        });
        Employee emp= IEmployeeMapper.INSTANCE.toEmployeeFromDto(dto);
    repository.save(emp);
    return true;
    }
}
