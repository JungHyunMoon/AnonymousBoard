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

    // 게시글 작성
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

    // 게시글 목록 조회
    public BoardResponseDtos getBoards() {
        BoardResponseDtos boardResponseDtos = new BoardResponseDtos();
        List<Board> boards = boardRepository.findAllByOrderByCreatedAtDesc();

        for (Board board : boards) {
            BoardResponseDto boardResponseDto = new BoardResponseDto(board);
            String username = board.getUsername();

            // 해당 사용자의 리스트가 이미 있는지 확인하고 없으면 새로 생성
            List<BoardResponseDto> userBoards = boardResponseDtos.getBoardResponseDtos().computeIfAbsent(username, k -> new ArrayList<>());

            // 해당 사용자의 게시글을 리스트에 추가
            userBoards.add(boardResponseDto);
        }

        return boardResponseDtos;
    }


    // 게시글 조회
    public BoardResponseDto getBoardById(long id) throws CustomException {
        Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        return new BoardResponseDto(board);
    }

    // 게시글 수정
    @Transactional
    public BoardResponseDto updateBoard(long id, UpdateBoardRequestDto requestDto) throws CustomException {
        Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        board.update(requestDto);

        return new BoardResponseDto(board);
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
