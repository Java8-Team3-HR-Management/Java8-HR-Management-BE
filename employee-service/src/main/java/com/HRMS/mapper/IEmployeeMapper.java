package com.HRMS.mapper;

import com.HRMS.dto.request.AddEmployeeRequestDto;
import com.HRMS.repository.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IEmployeeMapper {
    IEmployeeMapper INSTANCE = Mappers.getMapper(IEmployeeMapper.class);

    Employee toEmployeeFromDto(final AddEmployeeRequestDto dto);

}
