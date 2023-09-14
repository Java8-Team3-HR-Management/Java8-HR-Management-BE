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
        Boolean check = service.addPost(requestDto);
        if (check) {
            return ResponseEntity.ok(AddPostResponseDto.builder()
                    .result("Post added successfully.")
                    .build());
        } else {
            //
            return ResponseEntity.badRequest().body(AddPostResponseDto.builder()
                    .status(EStatus.PENDING)
                    .result("Lütfen girdiğiniz ayrıntıları kontrol ediniz.")
                    .build());
        }
    }

    @PutMapping(UPDATEPOST)
    public ResponseEntity<Boolean> updatePost(@RequestBody @Valid UpdatePostRequestDto dto) {
        return ResponseEntity.ok(service.updatePost(dto));
    }
}

