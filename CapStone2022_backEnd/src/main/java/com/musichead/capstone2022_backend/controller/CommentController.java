package com.musichead.capstone2022_backend.controller;

import com.musichead.capstone2022_backend.domain.comment.Comment;
import com.musichead.capstone2022_backend.dto.CommentDto;
import com.musichead.capstone2022_backend.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@AllArgsConstructor
@Controller
public class CommentController {

    private final CommentService commentService;

    @QueryMapping
    public List<Comment> comments() {
        return commentService.findAll();
    }

    @MutationMapping
    public Comment addComment(@Argument CommentDto commentDto) {
        return commentService.save(commentDto);
    }

    @MutationMapping
    public Comment updateComment(@Argument Long id, @Argument CommentDto commentDto) {
        return commentService.update(id, commentDto);
    }

    @MutationMapping
    public Comment deleteComment(@Argument Long id) {
        return commentService.delete(id);
    }
}