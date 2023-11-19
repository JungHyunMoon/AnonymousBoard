package com.anonymousboard.dto.request;

import com.anonymousboard.entity.Board;
import com.anonymousboard.entity.Comment;
import com.anonymousboard.entity.User;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    String content;

    public Comment toEntity(User user) {
        return Comment.builder()
                .content(content)
                .user(user)
                .build();
    }
}
