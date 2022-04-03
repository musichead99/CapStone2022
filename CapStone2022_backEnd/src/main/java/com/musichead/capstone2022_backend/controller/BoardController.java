package com.musichead.capstone2022_backend.controller;

import com.musichead.capstone2022_backend.domain.board.Board;
import com.musichead.capstone2022_backend.dto.BoardDto;
import com.musichead.capstone2022_backend.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/* 테스트용 */
@AllArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @QueryMapping
    public List<Board> boards() {
        return boardService.findAll();
    }

    @QueryMapping
    public Board getBoard(@Argument Long id) {
        return boardService.findById(id);
    }

    @MutationMapping
    public Board addBoard(BoardDto boardDto) {
        return boardService.save(boardDto);
    }
}
