package com.anonymousboard.controller;

import com.anonymousboard.dto.request.CreateBoardRequestDto;
import com.anonymousboard.dto.response.BoardListResponseDto;
import com.anonymousboard.dto.response.BoardResponseDto;
import com.anonymousboard.entity.Board;
import com.anonymousboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping()
    public BoardResponseDto createBoard(@RequestBody CreateBoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        boardService.saveBoard(board);

        return new BoardResponseDto(board);
    }

    @GetMapping()
    public BoardListResponseDto getBoards() {
        List<Board> boards = boardService.getBoards();
        BoardListResponseDto boardListResponseDto = new BoardListResponseDto();
        for (Board board : boards) {
            boardListResponseDto.addBoardResponseDto(new BoardResponseDto(board));
        }
        return boardListResponseDto;
    }

    @GetMapping("/{id}")
    public BoardResponseDto getBoardById(@PathVariable long id) {
        Board board = boardService.getBoardById(id);
        return new BoardResponseDto(board);
    }
}
