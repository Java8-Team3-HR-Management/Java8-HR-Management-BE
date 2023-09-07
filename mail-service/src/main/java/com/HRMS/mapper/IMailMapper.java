package com.HRMS.mapper;

import com.HRMS.dto.request.SendActivationMailRequestDto;
import com.HRMS.repository.IMailRepository;
import com.HRMS.repository.entity.Mail;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IMailMapper {
    IMailMapper INSTANCE = Mappers.getMapper(IMailMapper.class);
    Mail toMailFromDto(final SendActivationMailRequestDto dto);
}
