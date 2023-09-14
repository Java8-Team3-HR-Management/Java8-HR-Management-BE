package com.HRMS.repository;


import com.HRMS.repository.entity.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IManagerRepository extends MongoRepository<Manager, String> {
    Optional<Manager> findByEmail(String email);
}
