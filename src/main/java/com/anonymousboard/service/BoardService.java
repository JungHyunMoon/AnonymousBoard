package com.anonymousboard.service;

import com.anonymousboard.dto.request.UpdateBoardRequestDto;
import com.anonymousboard.entity.Board;
import com.anonymousboard.exception.CustomException;
import com.anonymousboard.exception.ErrorCode;
import com.anonymousboard.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 작성
    @Transactional
    public void saveBoard(Board board) {
        boardRepository.save(board);
    }

    // 게시글 목록 조회
    public List<Board> getBoards() {
        return boardRepository.findAllByOrderByCreatedAtDesc();
    }

    // 게시글 조회
    public Board getBoardById(long id) throws CustomException {
        return boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
    }

    // 게시글 수정
    @Transactional
    public Board updateBoard(long id, UpdateBoardRequestDto requestDto) throws CustomException {
        Board board = boardRepository.findById(id).orElse(null);
        if (board == null) {
            throw new CustomException(ErrorCode.BOARD_NOT_FOUND);
        }
        if (checkPwd(board, requestDto.getPassword())) {
            board.update(requestDto);
        } else {
            throw new CustomException(ErrorCode.BAD_PARAMETER);
        }
        return board;
    }

    // 비밀번호 일치 확인
    private boolean checkPwd(Board board, String password) {
        return board.getPassword().equals(password);
    }

    // 게시글 삭제
    @Transactional
    public void deleteBoard(long id, String password) throws CustomException {
        Board board = getBoardById(id);
        if (!checkPwd(board, password)) {
            throw new CustomException(ErrorCode.BAD_PARAMETER);
        }
        boardRepository.delete(board);
    }
}
