package com.HRMS.services;

import com.HRMS.dto.request.AddEmployeeRequestDto;
import com.HRMS.dto.request.ListPermissionsRequestDto;
import com.HRMS.dto.response.ListPermissionsResponseDto;
import com.HRMS.exceptions.EmployeeException;
import com.HRMS.exceptions.ErrorType;
import com.HRMS.mapper.IEmployeeMapper;
import com.HRMS.rabbitmq.model.CreateEmployee;
import com.HRMS.rabbitmq.model.SendActivationEmail;
import com.HRMS.rabbitmq.producer.EmployeeProducer;
import com.HRMS.rabbitmq.producer.SendActivationEmailProducer;
import com.HRMS.repository.IEmployeeRepository;
import com.HRMS.repository.entity.Employee;
import com.HRMS.utils.RandomPasswordGenerator;
import com.HRMS.utils.ServiceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.HRMS.utils.RandomPasswordGenerator.*;

@Service

public class EmployeeService extends ServiceManager<Employee,String> {
    private final IEmployeeRepository repository;
    private final EmployeeProducer employeeProducer;
    private final SendActivationEmailProducer emailProducer;

    public EmployeeService(IEmployeeRepository repository, EmployeeProducer employeeProducer, SendActivationEmailProducer emailProducer) {
        super(repository);
        this.repository = repository;
        this.employeeProducer = employeeProducer;
        this.emailProducer = emailProducer;
    }




    public Boolean addEmployee(AddEmployeeRequestDto dto){

       Optional<Employee> empOpt= repository.findOptionalByEmail(dto.getEmail());
        if (empOpt.isPresent()){
            throw new EmployeeException(ErrorType.EMPLOYEE_ALREADY_EXIST);
        } else {
            Employee emp= IEmployeeMapper.INSTANCE.toEmployeeFromDto(dto);
            save(emp);
        }
        String mailGen= dto.getNameSurname().toLowerCase().trim()+"@"+dto.getCompanyName().toLowerCase().trim()+".com";
        String pass= generateRandomPassword();
        employeeProducer.createEmployeeAtAuth(CreateEmployee.builder()
                .email(dto.getEmail())
                .password(pass)
                .build());
        emailProducer.sendMailActivationMessage(SendActivationEmail.builder()
                        .email(dto.getEmail())
                        .companyMail(mailGen)
                        .password(pass)
                .build());
    return true;
    }

    public ListPermissionsResponseDto listPermissionsByEmployeeId(ListPermissionsRequestDto requestDto) {
        Long employeeId = requestDto.getEmployeeId();
        Optional<Employee> optionalEmployee = repository.findOptionalByEmail(requestDto.getEmail());

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            ListPermissionsResponseDto responseDto = new ListPermissionsResponseDto();
            responseDto.setEmployeeId(employeeId);
            responseDto.setEmployeeName(employee.getNameSurname());

            return responseDto;

        } else {
            throw new EmployeeException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
    }
    public Optional<List<Employee>> findOptionalByCompanyName(String companyName) {
        Optional<List<Employee>> optionalEmployee = repository.findOptionalByCompanyName(companyName);
        if (optionalEmployee.isEmpty()) {
            throw new EmployeeException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        return repository.findOptionalByCompanyName(companyName);

    }
}
