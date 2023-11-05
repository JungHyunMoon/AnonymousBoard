package com.anonymousboard.dto.response;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BoardListResponseDto {
    private List<BoardResponseDto> boardList = new ArrayList<>();

    public void addBoardResponseDto(BoardResponseDto boardResponseDto) {
        this.boardList.add(boardResponseDto);
    }
}
