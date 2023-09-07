package com.HRMS.repository;

import com.HRMS.repository.entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAdminRepository extends MongoRepository<Admin,Long> {

    Optional<Admin> findOptionalByEmail(String email);


}
