package com.musichead.capstone2022_backend.service;

import com.musichead.capstone2022_backend.domain.board.Board;
import com.musichead.capstone2022_backend.domain.board.BoardRepository;
import com.musichead.capstone2022_backend.dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/* 테스트용 */

@AllArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public Board save(BoardDto boardDto) {
        Board board = Board.builder()
                .name(boardDto.getName())
                .build();

        return boardRepository.save(board);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Board findById(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        Board board = null;

        if(optionalBoard.isPresent()) {
            board = optionalBoard.get();
        }

        return board;
    }
}
