package com.HRMS.services;

import com.HRMS.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.HRMS.dto.requests.AddEmployeeRequestDto;

@Service
@RequiredArgsConstructor
public class UserService  {
    private final IUserRepository userRepository;

}
