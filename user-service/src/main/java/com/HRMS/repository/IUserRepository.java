package com.HRMS.repository;

import com.HRMS.repository.entity.User;
import org.mapstruct.control.MappingControl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends MongoRepository<User, Long> {

    Optional<User> findOptionalByEmail(String email);

}
