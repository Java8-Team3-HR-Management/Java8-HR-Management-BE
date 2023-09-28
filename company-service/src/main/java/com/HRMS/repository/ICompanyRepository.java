package com.HRMS.repository;

import com.HRMS.repository.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICompanyRepository extends MongoRepository<Company, String> {
    Optional<Company> findAllByCompanyNameAndTaxNumber(String companyName,Long taxNumber);
    Optional<List<Company>> findAllByStatus(String status);
    Optional<Company> findAllByTaxNumber(Long taxNumber);
    Optional<Company> findAllByCompanyName(String companyName);
    Company findByCompanyNameEqualsIgnoreCase(String companyName);

}
