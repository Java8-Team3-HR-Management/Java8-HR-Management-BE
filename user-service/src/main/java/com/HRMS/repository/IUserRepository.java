package com.HRMS.repository;

import com.HRMS.repository.entity.User;
import org.mapstruct.control.MappingControl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends MongoRepository<User, String> {

}
