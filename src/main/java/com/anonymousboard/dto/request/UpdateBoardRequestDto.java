package com.anonymousboard.dto.request;

import lombok.Getter;

@Getter
public class UpdateBoardRequestDto {
    private String title;
    private String author;
    private String password;
    private String content;
}
