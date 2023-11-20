package com.anonymousboard.controller;

import com.anonymousboard.dto.request.CommentRequestDto;
import com.anonymousboard.dto.response.CommentResponseDto;
import com.anonymousboard.entity.Comment;
import com.anonymousboard.exception.CustomException;
import com.anonymousboard.security.UserDetailsImpl;
import com.anonymousboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Comment comment = commentService.createComment(requestDto, userDetails);
        return new ResponseEntity<>(new CommentResponseDto(comment), HttpStatus.CREATED);
    }

    @PutMapping("/comment/{cmtId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable long cmtId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws CustomException {
        Comment comment = commentService.updateComment(cmtId, requestDto, userDetails);
        return new ResponseEntity<>(new CommentResponseDto(comment), HttpStatus.OK);
    }

    @DeleteMapping("comment/{cmtId}")
    public ResponseEntity<String> deleteComment(@PathVariable long cmtId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws CustomException {
        commentService.deleteComment(cmtId, requestDto, userDetails);
        return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
    }
}
