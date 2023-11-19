package com.anonymousboard.dto.response;

import com.anonymousboard.entity.Board;
import jakarta.persistence.Column;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
public class BoardResponseDto {
    private String title;
    private String username;
    private String content;
    private Date createdAt;
    private boolean status;

    public BoardResponseDto(Board board) {
        this.title = board.getTitle();
        this.username = board.getUsername();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();
        this.status = board.getStatus();
    }
}
