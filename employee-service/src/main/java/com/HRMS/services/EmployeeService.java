package com.HRMS.services;

import com.HRMS.dto.request.AddEmployeeRequestDto;
import com.HRMS.exceptions.EmployeeException;
import com.HRMS.exceptions.ErrorType;
import com.HRMS.mapper.IEmployeeMapper;
import com.HRMS.rabbitmq.model.CreateEmployee;
import com.HRMS.rabbitmq.producer.EmployeeProducer;
import com.HRMS.repository.IEmployeeRepository;
import com.HRMS.repository.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final IEmployeeRepository repository;
    private final EmployeeProducer producer;

    public Boolean addEmployee(AddEmployeeRequestDto dto){
        Boolean check=producer.createEmployeeAtAuth(CreateEmployee.builder()
                        .email(dto.getEmail())
                .build());
   if (check){
       Optional<Employee> empOpt= repository.findOptionalByEmail(dto.getEmail());
       if (empOpt.isPresent()) throw new EmployeeException(ErrorType.EMPLOYEE_ALREADY_EXIST);
   }
        Employee emp= IEmployeeMapper.INSTANCE.toEmployeeFromDto(dto);
    repository.save(emp);
    return true;
    }
}
