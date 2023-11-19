package com.anonymousboard.dto.request;

import lombok.Getter;

import java.util.Date;

@Getter
public class CreateBoardRequestDto {
    private String title;
    private String content;
}
