package com.HRMS.mapper;

import com.HRMS.dto.request.AddPostRequestDto;
import com.HRMS.dto.response.AddPostResponseDto;
import com.HRMS.dto.response.GetAllPendingPostResponseDto;
import com.HRMS.repository.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")

public interface IPostMapper {
    IPostMapper INSTANCE = Mappers.getMapper(IPostMapper.class);

    Post toPostFromDto(final AddPostRequestDto dto);

    GetAllPendingPostResponseDto toGetAllPendingResponseDtoFromPost(final Post post);

}
