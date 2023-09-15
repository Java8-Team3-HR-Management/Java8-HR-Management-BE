package com.HRMS.service;

import com.HRMS.dto.request.AddPostRequestDto;
import com.HRMS.dto.request.UpdatePostRequestDto;
import com.HRMS.dto.response.AddPostResponseDto;
import com.HRMS.dto.response.GetAllPendingPostResponseDto;
import com.HRMS.dto.response.UpdatePostResponseDto;
import com.HRMS.exceptions.PostException;
import com.HRMS.exceptions.ErrorType;
import com.HRMS.mapper.IPostMapper;
import com.HRMS.repository.IPostRepository;
import com.HRMS.repository.entity.Post;
import com.HRMS.repository.enums.EStatus;
import com.HRMS.utils.ServiceManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService extends ServiceManager<Post,String> {
    private final IPostRepository repository;


    public PostService(IPostRepository repository) {
        super(repository);
        this.repository = repository;
    }

   
        public Boolean addPost(AddPostRequestDto requestDto) {
            Optional<Post> postExists = repository.findByPostSubjectAndPostContent(requestDto.getPostSubject(), requestDto.getPostContent());

            if (postExists.isPresent()) {

                throw new PostException(ErrorType.POST_ALREADY_EXISTS);
            }
            Post post = IPostMapper.INSTANCE.toPostFromDto(requestDto);
            post.setStatus(EStatus.PENDING);
            save(post);
         return true;
         
        }
        
    
    public Boolean updatePost(UpdatePostRequestDto requestDto) {
        Optional<Post> postExists = repository.findById(requestDto.getPostId());
        if (!postExists.isPresent()) {
            throw new PostException(ErrorType.ID_NOT_FOUND);
        }
        Post existingPost = postExists.get();
        existingPost.setStatus(EStatus.APPROVED);
        update(existingPost);
      return true;
    }


    public List<GetAllPendingPostResponseDto> getAllPendingPost(){
        List<Post> getAllPending = repository.findAll();
        List<GetAllPendingPostResponseDto> posts = new ArrayList<>();
        for (Post post : getAllPending) {
            if (post.getStatus().equals("PENDING")){
                posts.add(IPostMapper.INSTANCE.toGetAllPendingResponseDtoFromPost(post));
            }
        }
        return posts;
    }




}



