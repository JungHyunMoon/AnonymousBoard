package com.anonymousboard.dto.response;

import com.anonymousboard.entity.Board;
import com.anonymousboard.entity.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class CommentResponseDto {
    private String content;

    public CommentResponseDto(Comment comment) {
        this.content = comment.getContent();
    }
}
