package com.HRMS.service;

import com.HRMS.dto.request.AddPostRequestDto;
import com.HRMS.dto.response.AddPostResponseDto;
import com.HRMS.exceptions.PostException;
import com.HRMS.exceptions.ErrorType;
import com.HRMS.mapper.IPostMapper;
import com.HRMS.repository.IPostRepository;
import com.HRMS.repository.entity.Post;
import com.HRMS.repository.enums.EStatus;
import com.HRMS.utils.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class PostService extends ServiceManager<Post,String> {
    private final IPostRepository repository;
    private final IPostMapper mapper;


    public PostService(IPostRepository repository,IPostMapper mapper) {
        super(repository);
        this.repository = repository;
        this.mapper=mapper;
    }



        public Boolean addPost(AddPostRequestDto requestDto) {
            Optional<Post> postExists = repository.findByPostSubjectAndPostContent(requestDto.getPostSubject(), requestDto.getPostContent());

            if (postExists.isPresent()) {

                throw new PostException(ErrorType.POST_ALREADY_EXISTS);
            }
            Post post = mapper.toPostFromDto(requestDto);
            post.setStatus(EStatus.PENDING);
            save(post);

/**
            Post post = Post.builder()
                    .companyName(requestDto.getCompanyName())
                    .companyId(requestDto.getCompanyId())
                    .postSubject(requestDto.getPostSubject())
                    .postContent(requestDto.getPostContent())
                    .employeeId(requestDto.getEmployeeId())
                    .employeeName(requestDto.getEmployeeName())
                    .status(EStatus.PENDING)
                    .build();
            save(post);
            **/

            AddPostResponseDto  responseDto = mapper.toDtoFromPost(post);
/**
            AddPostResponseDto responseDto = AddPostResponseDto.builder()
                    .id(post.getId())
                    .companyName(post.getCompanyName())
                    .companyId(post.getCompanyId())
                    .postSubject(post.getPostSubject())
                    .postContent(post.getPostContent())
                    .employeeId(post.getEmployeeId())
                    .employeeName(post.getEmployeeName())
                    .status(post.getStatus())
                    .build();
**/
            return true;
        }
}



