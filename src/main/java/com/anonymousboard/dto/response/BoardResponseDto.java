package com.anonymousboard.dto.response;

import com.anonymousboard.entity.Board;
import lombok.Getter;

import java.util.Date;

@Getter
public class BoardResponseDto {
    private String title;
    private String author;
    private String content;

    public BoardResponseDto(Board board) {
        this.title = board.getTitle();
        this.author = board.getAuthor();
        this.content = board.getContent();
    }
}
