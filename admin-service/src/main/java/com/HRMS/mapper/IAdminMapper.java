package com.HRMS.mapper;

import com.HRMS.dto.request.AddAdminRequestDto;
import com.HRMS.repository.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IAdminMapper {

    IAdminMapper INSTANCE = Mappers.getMapper(IAdminMapper.class);

    Admin toAdminFromDto(final AddAdminRequestDto dto);
}
