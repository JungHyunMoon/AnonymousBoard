package com.anonymousboard.service;

import com.anonymousboard.dto.request.CommentRequestDto;
import com.anonymousboard.entity.Comment;
import com.anonymousboard.entity.User;
import com.anonymousboard.repository.CommentRepository;
import com.anonymousboard.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment createComment(CommentRequestDto requestDto, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        Comment comment = requestDto.toEntity(user);
        commentRepository.save(requestDto.toEntity(user));
        return comment;
    }
}
