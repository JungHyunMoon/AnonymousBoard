package com.anonymousboard.service;

import com.anonymousboard.dto.response.BoardListResponseDto;
import com.anonymousboard.dto.response.BoardResponseDto;
import com.anonymousboard.entity.Board;
import com.anonymousboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public void saveBoard(Board board) {
        boardRepository.save(board);
    }

    public List<Board> getBoards() {
        List<Board> boards = boardRepository.findAll();


        return boards;
    }

    public Board getBoardById(long id) {
        return boardRepository.findById(id).orElse(null);
    }
}
