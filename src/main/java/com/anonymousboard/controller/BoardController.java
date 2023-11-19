package com.anonymousboard.controller;

import com.anonymousboard.dto.request.CreateBoardRequestDto;
import com.anonymousboard.dto.request.UpdateBoardRequestDto;
import com.anonymousboard.dto.response.BoardResponseDtos;
import com.anonymousboard.dto.response.BoardResponseDto;
import com.anonymousboard.entity.Board;
import com.anonymousboard.exception.CustomException;
import com.anonymousboard.jwt.JwtUtil;
import com.anonymousboard.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;
    private final JwtUtil jwtUtil;

    @PostMapping("/board")
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody CreateBoardRequestDto requestDto, HttpServletRequest req) throws CustomException {
        Board board = new Board(requestDto);
        boardService.saveBoard(board, req);
        return new ResponseEntity<>(new BoardResponseDto(board), HttpStatus.CREATED);
    }

    @GetMapping("/board")
    public ResponseEntity<BoardResponseDtos> getBoards() {
        BoardResponseDtos boardResponseDtos = boardService.getBoards();
        return new ResponseEntity<>(boardResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<BoardResponseDto> getBoardById(@PathVariable long id) throws CustomException {
        BoardResponseDto boardResponseDto = boardService.getBoardById(id);
        return new ResponseEntity<>(boardResponseDto, HttpStatus.OK);
    }

    @PutMapping("/board")
    public ResponseEntity<BoardResponseDto> updateBoard(@RequestHeader long id, @RequestBody UpdateBoardRequestDto requestDto, HttpServletRequest req) throws CustomException {

        BoardResponseDto boardResponseDto = boardService.updateBoard(id, requestDto);
        return new ResponseEntity<>(boardResponseDto, HttpStatus.OK);
    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteBoard(@PathVariable long id, @RequestHeader String password) throws CustomException {
//        boardService.deleteBoard(id, password);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
