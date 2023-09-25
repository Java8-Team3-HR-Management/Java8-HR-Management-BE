package com.HRMS.repository;

import com.HRMS.repository.entity.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IExpenseRepository extends MongoRepository<Expense, String> {


}
