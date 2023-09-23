package com.HRMS.repository;

import com.HRMS.repository.entity.Debt;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDebtRepository extends MongoRepository<Debt, String> {
    Optional<Debt> findByUserId(String userId);
}
