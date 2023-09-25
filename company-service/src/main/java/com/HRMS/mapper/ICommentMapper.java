package com.HRMS.mapper;

import com.HRMS.dto.request.AddCommentRequestDto;
import com.HRMS.repository.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICommentMapper {
    ICommentMapper INSTANCE = Mappers.getMapper(ICommentMapper.class);

    Comment toCommentFromDto(final AddCommentRequestDto dto);
}
