package com.HRMS.mapper;

import com.HRMS.dto.response.ViewAllEmployeeInfoResponseDto;
import com.HRMS.rabbitmq.model.CreateEmployee;
import com.HRMS.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User toUserFromModel(final CreateEmployee employee);
    ViewAllEmployeeInfoResponseDto toViewAllEmployeeInfoResponseDtoFromUser(final User user);
}
