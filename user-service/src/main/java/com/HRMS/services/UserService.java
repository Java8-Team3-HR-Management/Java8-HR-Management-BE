package com.HRMS.services;

import com.HRMS.exceptions.ErrorType;
import com.HRMS.exceptions.UserException;
import com.HRMS.rabbitmq.model.CreateProfile;
import com.HRMS.repository.IUserRepository;
import com.HRMS.repository.entity.User;
import com.HRMS.utils.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service

public class UserService extends ServiceManager<User,Long> {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    public Boolean createGuest(CreateProfile profile) {
        Optional<User> optionalUser = userRepository.findOptionalByEmail(profile.getEmail());
        if (optionalUser.isPresent()) {
            throw new UserException(ErrorType.GUEST_ALREADY_EXIST);
        }
        User user = User.builder()

                .authid(profile.getAuthid())
                .email(profile.getEmail())
                .password(profile.getPassword())
                .nameSurname(profile.getNameSurname())
                .build();
        return true;

    }
}
