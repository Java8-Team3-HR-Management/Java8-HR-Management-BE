package com.HRMS.mapper;

import com.HRMS.dto.request.AddCompanyRequestDto;
import com.HRMS.dto.response.GetAllCompanyResponseDto;
import com.HRMS.repository.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface ICompanyMapper {
    ICompanyMapper INSTANCE = Mappers.getMapper(ICompanyMapper.class);

    Company toCompanyFromDto(final AddCompanyRequestDto addCompanyRequestDto);
    GetAllCompanyResponseDto toGetAllCompanyResponseDtoFromCompany(final Company company);
}
