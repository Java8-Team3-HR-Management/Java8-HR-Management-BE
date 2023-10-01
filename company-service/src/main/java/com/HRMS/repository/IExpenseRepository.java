package com.HRMS.repository;

import com.HRMS.repository.entity.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IExpenseRepository extends MongoRepository<Expense, String> {

   List<Expense> findByCompanyId(String companyId);
}
