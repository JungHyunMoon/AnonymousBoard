package com.anonymousboard.controller;

import com.anonymousboard.dto.request.CreateBoardRequestDto;
import com.anonymousboard.dto.request.UpdateBoardRequestDto;
import com.anonymousboard.dto.response.BoardResponseDtos;
import com.anonymousboard.dto.response.BoardResponseDto;
import com.anonymousboard.entity.Board;
import com.anonymousboard.exception.CustomException;
import com.anonymousboard.security.UserDetailsImpl;
import com.anonymousboard.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;

    /**
     * 할일카드 작성
     * @param requestDto
     * @param req
     * @return
     * @throws CustomException
     */
    @PostMapping("/board")
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody CreateBoardRequestDto requestDto, HttpServletRequest req) throws CustomException {
        Board board = new Board(requestDto);
        boardService.saveBoard(board, req);
        return new ResponseEntity<>(new BoardResponseDto(board), HttpStatus.CREATED);
    }

    /**
     * 할일카드 목록 조회
     * @return
     */
    @GetMapping("/board")
    public ResponseEntity<BoardResponseDtos> getBoards() {
        BoardResponseDtos boardResponseDtos = boardService.getBoards();
        return new ResponseEntity<>(boardResponseDtos, HttpStatus.OK);
    }

    /**
     * 할일카드  조회
     * @param id
     * @return
     * @throws CustomException
     */
    @GetMapping("/board/{id}")
    public ResponseEntity<BoardResponseDto> getBoardById(@PathVariable long id) throws CustomException {
        BoardResponseDto boardResponseDto = boardService.getBoardById(id);
        return new ResponseEntity<>(boardResponseDto, HttpStatus.OK);
    }

    /**
     * 할일카드 수정
     * @param id
     * @param requestDto
     * @param userDetails
     * @return
     * @throws CustomException
     */
    @PutMapping("/board")
    public ResponseEntity<BoardResponseDto> updateBoard(@RequestHeader long id, @RequestBody UpdateBoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws CustomException {
        BoardResponseDto boardResponseDto = boardService.updateBoard(id, requestDto, userDetails);
        return new ResponseEntity<>(boardResponseDto, HttpStatus.OK);
    }

    /**
     * 할일카드 완료
     * @param id
     * @param userDetails
     * @return
     * @throws CustomException
     */
    @PutMapping("/board/complete-status/{id}")
    public ResponseEntity<String> markTaskAsComplete(@PathVariable long id, @AuthenticationPrincipal UserDetailsImpl userDetails) throws CustomException {
        boardService.markTaskAsComplete(id, userDetails);
        return ResponseEntity.status(HttpStatus.OK).body("상태 변경 완료");
    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteBoard(@PathVariable long id, @RequestHeader String password) throws CustomException {
//        boardService.deleteBoard(id, password);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
