package com.HRMS.services;

import com.HRMS.dto.requests.UpdateEmployeeRequestDto;
import com.HRMS.dto.requests.ViewAllEmployeeInfoRequestDto;
import com.HRMS.dto.response.ViewAllEmployeeInfoResponseDto;
import com.HRMS.exceptions.ErrorType;
import com.HRMS.exceptions.UserException;
import com.HRMS.mapper.IUserMapper;
import com.HRMS.rabbitmq.model.CreateAdmin;
import com.HRMS.rabbitmq.model.CreateEmployee;
import com.HRMS.rabbitmq.model.CreateManager;
import com.HRMS.rabbitmq.model.CreateProfile;
import com.HRMS.repository.IUserRepository;
import com.HRMS.repository.entity.User;
import com.HRMS.repository.enums.EContractStatement;
import com.HRMS.repository.enums.ERole;
import com.HRMS.repository.enums.EStatus;
import com.HRMS.utils.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service

public class UserService extends ServiceManager<User,Long> {
    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    public UserService(IUserRepository userRepository,IUserMapper userMapper) {
        super(userRepository);
        this.userRepository = userRepository;
        this.userMapper=userMapper;
    }

    public Boolean createGuest(CreateProfile profile) {
        Optional<User> optionalUser = userRepository.findOptionalByEmail(profile.getEmail());
        if (!optionalUser.isEmpty()) {
            throw new UserException(ErrorType.GUEST_ALREADY_EXIST);
        }
        User user = User.builder()
                .authId(profile.getAuthId())
                .email(profile.getEmail())
                .name(profile.getName())
                .surname(profile.getSurname())
                .role(ERole.GUEST)
                .build();
        save(user);
        return true;
    }
    public Boolean createEmployee(CreateEmployee employee) {
        Optional<User> optionalUser = userRepository.findOptionalByEmail(employee.getEmail());
        if (optionalUser.isEmpty()) {
            User user= userMapper.toUserFromModel(employee);
            user.setRole(ERole.EMPLOYEE);
            user.setContractStatement(EContractStatement.ACTIVE);
            user.setStatus(EStatus.ACTIVE);
            save(user);
            return true;
        }
        throw new UserException(ErrorType.EMPLOYEE_ALREADY_EXIST);
    }
    public List<User> findAllEmployees(String id) {
        Optional<List<User>> optionalEmployee = userRepository.findOptionalByCompanyId(id);
        if (optionalEmployee.isEmpty()) {
            throw new UserException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        List<User> users=new ArrayList<>();
        for (User user:optionalEmployee.get()) {
            if (user.getRole()==ERole.EMPLOYEE)
            {
                users.add(user);
            }
        }
        return users;

    }
    public User getEmployeeByAuthId(Long authId){
        Optional<User> optionalEmployee = userRepository.findOptionalByAuthId(authId);
        if(optionalEmployee.isEmpty())
            throw new UserException(ErrorType.EMPLOYEE_NOT_FOUND);
        return optionalEmployee.get();
    }
    public Boolean updateEmployee(UpdateEmployeeRequestDto requestDto) {
        Optional<User> employeeExists = userRepository.findById(requestDto.getId());
        if (employeeExists.isEmpty()) {
            throw new UserException(ErrorType.ID_NOT_FOUND);
        }
        User existingEmployee = employeeExists.get();
        existingEmployee.setName(requestDto.getName());
        existingEmployee.setSurname(requestDto.getSurname());
        existingEmployee.setEmail(requestDto.getEmail());
        existingEmployee.setPhone(requestDto.getPhone());
        update(existingEmployee);
        return true;
    }
    public Optional<ViewAllEmployeeInfoResponseDto>viewAllEmployeeInfo(ViewAllEmployeeInfoRequestDto requestDto) {

        Optional<User> employees = userRepository.findByCompanyIdAndDepartment(requestDto.getCompanyId(), requestDto.getDepartment());

        if (employees.isEmpty() ||!(employees.get().getRole()==ERole.EMPLOYEE)) {
            throw new UserException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        if (employees.get().getRole()==ERole.EMPLOYEE){
            User employee = employees.get();

            ViewAllEmployeeInfoResponseDto responseDto = IUserMapper.INSTANCE.toViewAllEmployeeInfoResponseDtoFromUser(employee);
            return Optional.of(responseDto);
        }
          return null;
    }
    public Boolean createAdmin(CreateAdmin admin) {
        Optional<User> optionalUser = userRepository.findOptionalByEmail(admin.getEmail());
        if (optionalUser.isEmpty()) {
            User user= User.builder()
                    .name(admin.getName())
                    .surname(admin.getSurname())
                    .authId(admin.getAuthId())
                    .email(admin.getEmail())
                    .role(ERole.ADMIN)
                    .status(EStatus.ACTIVE)
                    .build();
            save(user);
            return true;
        }
        throw new UserException(ErrorType.EMPLOYEE_ALREADY_EXIST);
    }
    public Boolean createManager(CreateManager manager) {
        Optional<User> optionalUser = userRepository.findOptionalByEmail(manager.getEmail());
        if (optionalUser.isEmpty()) {
            User user= User.builder()
                    .name(manager.getName())
                    .surname(manager.getSurname())
                    .authId(manager.getAuthId())
                    .email(manager.getEmail())
                    .companyEmail(manager.getCompanyEmail())
                    .companyName(manager.getCompanyName())
                    .role(ERole.MANAGER)
                    .status(EStatus.ACTIVE)
                    .contractStatement(EContractStatement.ACTIVE)
                    .build();
            save(user);
            return true;
        }
        throw new UserException(ErrorType.EMPLOYEE_ALREADY_EXIST);
    }


}
