package com.anonymousboard.controller;

import com.anonymousboard.dto.request.CreateBoardRequestDto;
import com.anonymousboard.dto.request.UpdateBoardRequestDto;
import com.anonymousboard.dto.response.BoardListResponseDto;
import com.anonymousboard.dto.response.BoardResponseDto;
import com.anonymousboard.entity.Board;
import com.anonymousboard.exception.CustomException;
import com.anonymousboard.exception.ErrorCode;
import com.anonymousboard.jwt.JwtUtil;
import com.anonymousboard.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;
    private final JwtUtil jwtUtil;

    @PostMapping()
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody CreateBoardRequestDto requestDto, HttpServletRequest req) throws CustomException {
        Board board = new Board(requestDto);
        boardService.saveBoard(board, req);
        return new ResponseEntity<>(new BoardResponseDto(board), HttpStatus.CREATED);
    }

//    @GetMapping()
//    public ResponseEntity<BoardListResponseDto> getBoards() {
//        List<Board> boards = boardService.getBoards();
//        BoardListResponseDto boardListResponseDto = new BoardListResponseDto();
//        for (Board board : boards) {
//            boardListResponseDto.addBoardResponseDto(new BoardResponseDto(board));
//        }
//        return new ResponseEntity<>(boardListResponseDto, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<BoardResponseDto> getBoardById(@PathVariable long id) throws CustomException {
//        Board board = boardService.getBoardById(id);
//        if (board == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(new BoardResponseDto(board), HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable long id, @RequestBody UpdateBoardRequestDto requestDto) throws CustomException {
//
//        Board board = boardService.updateBoard(id, requestDto);
//        return new ResponseEntity<>(new BoardResponseDto(board), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteBoard(@PathVariable long id, @RequestHeader String password) throws CustomException {
//        boardService.deleteBoard(id, password);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
