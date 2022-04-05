package com.musichead.capstone2022_backend.controller;

import com.musichead.capstone2022_backend.domain.post.Post;
import com.musichead.capstone2022_backend.dto.PostDto;
import com.musichead.capstone2022_backend.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@AllArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    @QueryMapping
    public List<Post> posts() {
        return postService.findAll();
    }

    @QueryMapping
    public Post getPost(@Argument Long id) {
        return postService.findById(id);
    }

    @MutationMapping
    public Post addPost(@Argument PostDto postDto) {
        return postService.save(postDto);
    }

    @MutationMapping
    public Post updatePost(@Argument Long id, @Argument PostDto postDto) {
        return postService.update(id, postDto);
    }

    @MutationMapping
    public Post deletePost(@Argument Long id) {
        return postService.delete(id);
    }
}
