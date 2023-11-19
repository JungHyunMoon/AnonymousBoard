package com.anonymousboard.service;

import com.anonymousboard.dto.request.UpdateBoardRequestDto;
import com.anonymousboard.dto.response.BoardResponseDtos;
import com.anonymousboard.dto.response.BoardResponseDto;
import com.anonymousboard.entity.Board;
import com.anonymousboard.exception.CustomException;
import com.anonymousboard.exception.ErrorCode;
import com.anonymousboard.jwt.JwtUtil;
import com.anonymousboard.repository.BoardRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public void saveBoard(Board board, HttpServletRequest req) throws CustomException {
        String rawToken = jwtUtil.getTokenFromRequest(req);

        String token = jwtUtil.substringToken(rawToken);
        board.setUsername(jwtUtil.getUsernameFromToken(token));

        if (jwtUtil.validateToken(token)) {
            boardRepository.save(board);
        } else {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
        boardRepository.save(board);
    }

    public BoardResponseDtos getBoards() {
        BoardResponseDtos boardResponseDtos = new BoardResponseDtos();
        List<Board> boards = boardRepository.findAllByOrderByCreatedAtDesc();

        for (Board board : boards) {
            BoardResponseDto boardResponseDto = new BoardResponseDto(board);
            String username = board.getUsername();

            List<BoardResponseDto> userBoards = boardResponseDtos.getBoardResponseDtos().computeIfAbsent(username, k -> new ArrayList<>());
            userBoards.add(boardResponseDto);
        }

        return boardResponseDtos;
    }

    public Board getBoardByIdOrThrow(long id) throws CustomException {
        return boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
    }

    public BoardResponseDto getBoardById(long id) throws CustomException {
        Board board = getBoardByIdOrThrow(id);
        return new BoardResponseDto(board);
    }

    @Transactional
    public BoardResponseDto updateBoard(long id, UpdateBoardRequestDto requestDto) throws CustomException {
        Board board = getBoardByIdOrThrow(id);
        board.update(requestDto);

        return new BoardResponseDto(board);
    }

    @Transactional
    public void markTaskAsComplete(long id) throws CustomException {
        Board board = getBoardByIdOrThrow(id);
        board.setStatus(true);
    }





    // 비밀번호 일치 확인
//    private boolean checkPwd(Board board, String password) {
//        return board.getPassword().equals(password);
//    }
//
//    // 게시글 삭제
//    @Transactional
//    public void deleteBoard(long id, String password) throws CustomException {
//        Board board = getBoardById(id);
//        if (!checkPwd(board, password)) {
//            throw new CustomException(ErrorCode.BAD_PARAMETER);
//        }
//        boardRepository.delete(board);
//    }
}
