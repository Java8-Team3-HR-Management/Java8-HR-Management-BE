package com.HRMS.controller;

import com.HRMS.dto.request.AddCommentRequestDto;
import com.HRMS.dto.request.UpdateCommentRequestDto;
import com.HRMS.dto.response.AddCommentResponseDto;
import com.HRMS.repository.entity.Comment;
import com.HRMS.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.HRMS.constants.RestApiList.*;

@RestController
@RequestMapping(COMMENT)
@RequiredArgsConstructor
@CrossOrigin("*")
public class CommentController {
    private final CommentService service;

    @PostMapping("/add-comment/{token}")
    public ResponseEntity<AddCommentResponseDto> addComment(@RequestBody AddCommentRequestDto requestDto, @PathVariable String token) {
        Boolean check = service.addComment(requestDto, token);
        if (check) {
            return ResponseEntity.ok(AddCommentResponseDto.builder().status(statusSuccess).result(messageSuccess).build());
        }
        return ResponseEntity.badRequest().body(AddCommentResponseDto.builder().status(statusFailed).result(messageFailed).build());
    }
    @PutMapping("/update-comment/{token}")
    public ResponseEntity<AddCommentResponseDto> updateComment(@RequestBody UpdateCommentRequestDto requestDto, @PathVariable String token){
        Boolean check = service.updateComment(requestDto,token);
        if(check) {
            return ResponseEntity.ok(AddCommentResponseDto.builder().status(statusSuccess).result(messageSuccess).build());
        }
        return ResponseEntity.badRequest().body(AddCommentResponseDto.builder().status(statusFailed).result(messageFailed).build());
    }
    @GetMapping("/get-all-pending-comment")
    public ResponseEntity<List<Comment>> getAllPendingComment() {
        return ResponseEntity.ok(service.getAllPendingComment());
    }
    @GetMapping("/get-all-approved-comment/{companyId}")
    public ResponseEntity<List<Comment>> getAllApprovedComment(String companyId) {
        return ResponseEntity.ok(service.getAllApprovedComment(companyId));
    }
}
