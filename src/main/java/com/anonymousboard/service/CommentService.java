package com.anonymousboard.service;

import com.anonymousboard.dto.request.CommentRequestDto;
import com.anonymousboard.entity.Comment;
import com.anonymousboard.entity.User;
import com.anonymousboard.exception.CustomException;
import com.anonymousboard.exception.ErrorCode;
import com.anonymousboard.repository.CommentRepository;
import com.anonymousboard.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
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

    @Transactional
    public Comment updateComment(long cmtId, CommentRequestDto requestDto, UserDetailsImpl userDetails) throws CustomException {
        Optional<Comment> checkComment = commentRepository.findById(cmtId);
        if (checkComment.isEmpty()) {
            throw new CustomException(ErrorCode.COMMENT_NOT_FOUND);
        } else {
            Comment comment = checkComment.get();
            if (comment.getUser().equals(userDetails.getUser())) {
                comment.update(requestDto);
                return comment;
            } else {
                throw new CustomException(ErrorCode.NO_AUTHORIZATION);
            }
        }
    }

    @Transactional
    public void deleteComment(long cmtId, CommentRequestDto requestDto, UserDetailsImpl userDetails) throws CustomException {
        Optional<Comment> checkComment = commentRepository.findById(cmtId);
        if (checkComment.isEmpty()) {
            throw new CustomException(ErrorCode.COMMENT_NOT_FOUND);
        } else {
            Comment comment = checkComment.get();
            if (comment.getUser().equals(userDetails.getUser())) {

                commentRepository.delete(comment);
            } else {
                throw new CustomException(ErrorCode.NO_AUTHORIZATION);
            }
        }
    }
}
