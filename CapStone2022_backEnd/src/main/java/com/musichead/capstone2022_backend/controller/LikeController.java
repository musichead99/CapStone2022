package com.musichead.capstone2022_backend.controller;

import com.musichead.capstone2022_backend.domain.like.Like;
import com.musichead.capstone2022_backend.domain.post.Post;
import com.musichead.capstone2022_backend.dto.LikeOutputDto;
import com.musichead.capstone2022_backend.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class LikeController {

    private final LikeService likeService;

    @QueryMapping
    public Long getLikeCountByPostId(@Argument Long postId) {
        return likeService.getLikeCount(postId);
    }

    @MutationMapping
    public LikeOutputDto addLike(@Argument Long postId, @Argument Long memberId) {
        return likeService.save(postId, memberId);
    }

    @MutationMapping
    public LikeOutputDto deleteLike(@Argument Long postId, @Argument Long memberId) {
        return likeService.delete(postId, memberId);
    }

    @SchemaMapping(typeName = "Post")
    public Long likeCount(Post post) {
        return likeService.getLikeCount(post);
    }
}
