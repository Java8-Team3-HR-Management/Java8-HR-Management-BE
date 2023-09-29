package com.HRMS.services;

import com.HRMS.dto.request.AddCompanyRequestDto;
import com.HRMS.dto.request.UpdateCompanyRequestDto;
import com.HRMS.dto.response.CompanyAverageRateResponseDto;
import com.HRMS.dto.response.GetAllCompanyResponseDto;
import com.HRMS.exceptions.CompanyException;
import com.HRMS.exceptions.ErrorType;
import com.HRMS.mapper.ICompanyMapper;
import com.HRMS.repository.ICommentRepository;
import com.HRMS.repository.ICompanyRepository;
import com.HRMS.repository.entity.Comment;
import com.HRMS.repository.entity.Company;
import com.HRMS.repository.enums.EStatus;
import com.HRMS.utils.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CompanyService extends ServiceManager<Company, String> {
    private final ICompanyRepository companyRepository;
    private final ICommentRepository commentRepository;

    public CompanyService(ICompanyRepository companyRepository,ICommentRepository commentRepository) {
        super(companyRepository);
        this.companyRepository = companyRepository;
        this.commentRepository= commentRepository;
    }

    public Boolean addCompany(AddCompanyRequestDto dto) {
        Optional<Company> optCom = companyRepository.findAllByCompanyNameAndTaxNumber(dto.getCompanyName(), dto.getTaxNumber());
        if (optCom.isPresent()) {
            throw new CompanyException(ErrorType.COMPANY_ALREADY_EXISTS);
        } else {
            Company company = ICompanyMapper.INSTANCE.toCompanyFromDto(dto);
            save(company);
        }

        return true;
    }

    public List<GetAllCompanyResponseDto> getAllCompanies() {
        List<Company> optCompanies = companyRepository.findAll();
        List<GetAllCompanyResponseDto> companies = new ArrayList<>();
        for (Company company : optCompanies) {
            //   if (company.getStatus().equals("ACTIVATED")) {
            companies.add(ICompanyMapper.INSTANCE.toGetAllCompanyResponseDtoFromCompany(company));
            //  }
        }
        return companies;
    }

    public List<GetAllCompanyResponseDto> getAllCompaniesWaitingForApproval() {
        List<Company> optCompaniesForApproval = companyRepository.findAll();
        List<GetAllCompanyResponseDto> companies = new ArrayList<>();
        for (Company company : optCompaniesForApproval) {
            if (company.getStatus() == EStatus.PENDING) {
                companies.add(ICompanyMapper.INSTANCE.toGetAllCompanyResponseDtoFromCompany(company));
            }
        }
        return companies;
    }

    public Boolean updateCompany(UpdateCompanyRequestDto dto) {
        Optional<Company> optCom = companyRepository.findById(dto.getId());
        if (optCom.isEmpty()) {
            throw new CompanyException(ErrorType.COMPANY_NOT_FOUND);
        }
        optCom.get().setStatus(dto.getStatus());
        update(optCom.get());
        return true;
    }

    public GetAllCompanyResponseDto getCompanyByName(String name) {
        Optional<Company> optCompany = companyRepository.findAllByCompanyName(name);
        if (optCompany.get().getStatus().equals("PENDING")) {
            throw new CompanyException(ErrorType.COMPANY_NOT_FOUND);
        }
        GetAllCompanyResponseDto company = ICompanyMapper.INSTANCE.toGetAllCompanyResponseDtoFromCompany(optCompany.get());

        return company;
    }
    public CompanyAverageRateResponseDto getCompanyAverageRate(String companyId) {

        Optional<List<Comment>> optComments = commentRepository.findAllByCompanyId(companyId);
        List<Comment> commentList = optComments.get().stream().filter(comment ->  comment.getStatus().equals(EStatus.APPROVED)).toList();
        Double avg = 0.0;
        for (Comment comment : commentList) {
            avg += comment.getRate();
        }
        int size = commentList.size();
        Double finalAvg = avg / size;
        Company company = companyRepository.findById(companyId).get();
        company.setTotalRate(finalAvg);
        return CompanyAverageRateResponseDto.builder()
                .size(size)
                .avgRate(finalAvg)
                .build();
    }
    }
