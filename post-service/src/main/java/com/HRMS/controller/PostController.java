package com.HRMS.controller;

import com.HRMS.dto.request.AddPostRequestDto;
import com.HRMS.dto.request.UpdatePostRequestDto;
import com.HRMS.dto.response.AddPostResponseDto;
import com.HRMS.dto.response.UpdatePostResponseDto;
import com.HRMS.repository.entity.Post;
import com.HRMS.repository.enums.EStatus;
import com.HRMS.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.HRMS.constants.RestApiList.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(POST)
public class PostController {
    private final PostService service;

    @PostMapping(ADDPOST)
    public ResponseEntity<AddPostResponseDto> addPost(@RequestBody @Valid AddPostRequestDto requestDto) {
        AddPostResponseDto responseDto = service.addPost(requestDto);

        if (responseDto != null) {
            return ResponseEntity.ok(responseDto);
        } else {
            //
            return ResponseEntity.badRequest().body(AddPostResponseDto.builder()
                    .status(EStatus.PENDING)
                    .result("Lütfen girdiğiniz ayrıntıları kontrol ediniz.")
                    .build());
        }
    }

    @PutMapping(UPDATEPOST)
    public ResponseEntity<UpdatePostResponseDto> updatePost(@PathVariable String postId, @RequestBody @Valid UpdatePostRequestDto requestDto) {
        UpdatePostResponseDto responseDto = service.updatePost(postId, requestDto);
        return ResponseEntity.ok(responseDto);
    }




}
