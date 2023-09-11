package com.HRMS.services;

import com.HRMS.dto.request.AddCompanyRequestDto;
import com.HRMS.dto.request.UpdateCompanyRequestDto;
import com.HRMS.dto.response.GetAllCompanyResponseDto;
import com.HRMS.exceptions.CompanyException;
import com.HRMS.exceptions.ErrorType;
import com.HRMS.mapper.ICompanyMapper;
import com.HRMS.repository.ICompanyRepository;
import com.HRMS.repository.entity.Company;
import com.HRMS.repository.enums.EStatus;
import com.HRMS.utils.ServiceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class CompanyService extends ServiceManager<Company,String> {
    private final ICompanyRepository repository;

    public CompanyService(ICompanyRepository repository) {
        super(repository);
        this.repository=repository;
    }

    public Boolean addCompany(AddCompanyRequestDto dto){
    Optional<Company> optCom = repository.findAllByCompanyNameAndTaxNumber(dto.getCompanyName(),dto.getTaxNumber());
   if (optCom.isPresent()){
       throw new CompanyException(ErrorType.COMPANY_ALREADY_EXISTS);
   }
   Company company= ICompanyMapper.INSTANCE.toCompanyFromDto(dto);
    return true;
}
public List<GetAllCompanyResponseDto> getAllCompanies(){
List<Company> optCompanies = repository.findAll();
List<GetAllCompanyResponseDto> companies = new ArrayList<>();
for (Company company : optCompanies) {
   if (company.getStatus().equals("ACTIVATED")){
       companies.add(ICompanyMapper.INSTANCE.toGetAllCompanyResponseDtoFromCompany(company));
   }
}
return companies;
    }

    public List<GetAllCompanyResponseDto> getAllCompaniesWaitingForApproval(){
        List<Company> optCompaniesForApproval = repository.findAll();
        List<GetAllCompanyResponseDto> companies = new ArrayList<>();
        for (Company company : optCompaniesForApproval) {
            if (company.getStatus().equals("PENDING")){
                companies.add(ICompanyMapper.INSTANCE.toGetAllCompanyResponseDtoFromCompany(company));
            }
        }
        return companies;
    }
    public Boolean updateCompany(UpdateCompanyRequestDto dto) {
        Optional<Company> optCom = repository.findAllByTaxNumber(dto.getTaxNumber());
        if (optCom.isEmpty()){
            throw new CompanyException(ErrorType.COMPANY_NOT_FOUND);
        }
        optCom.get().setStatus(EStatus.ACTIVATED);
        update(optCom.get());
        return true;
    }

}