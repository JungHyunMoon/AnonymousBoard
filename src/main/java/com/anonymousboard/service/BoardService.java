package com.anonymousboard.service;

import com.anonymousboard.dto.request.CreateBoardRequestDto;
import com.anonymousboard.entity.Board;
import com.anonymousboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public void saveBoard(Board board) {
        boardRepository.save(board);
    }
}
