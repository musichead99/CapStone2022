package com.musichead.capstone2022_backend.controller;

import com.musichead.capstone2022_backend.domain.comment.Comment;
import com.musichead.capstone2022_backend.domain.post.Post;
import com.musichead.capstone2022_backend.dto.CommentDto;
import com.musichead.capstone2022_backend.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
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

    @SchemaMapping(typeName = "Post")
    public List<Comment> comments(Post post) {
        return commentService.findByPostId(post);
    }
}
