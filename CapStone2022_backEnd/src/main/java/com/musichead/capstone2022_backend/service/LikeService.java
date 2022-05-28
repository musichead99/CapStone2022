package com.musichead.capstone2022_backend.service;

import com.musichead.capstone2022_backend.domain.like.Like;
import com.musichead.capstone2022_backend.domain.like.LikeRepository;
import com.musichead.capstone2022_backend.domain.member.Member;
import com.musichead.capstone2022_backend.domain.member.MemberRepository;
import com.musichead.capstone2022_backend.domain.post.Post;
import com.musichead.capstone2022_backend.domain.post.PostRepository;
import com.musichead.capstone2022_backend.domain.subscribe.Subscribe;
import com.musichead.capstone2022_backend.dto.LikeOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public Long getLikeCount(Post post) {
        return likeRepository.countByPost(post);
    }

    public Long getLikeCount(Long postId) {
        Post post = postRepository.getReferenceById(postId);
        return likeRepository.countByPost(post);
    }

    public LikeOutputDto save(Long postId, Long memberId) {
        Post post = postRepository.getReferenceById(postId);
        Member member = memberRepository.getReferenceById(memberId);

        Like like = Like.builder()
                .post(post)
                .member(member)
                .build();
        likeRepository.save(like);
        Long likeCount = likeRepository.countByPost(post);

        return new LikeOutputDto(member, post, likeCount);
    }

    public LikeOutputDto delete(Long postId, Long memberId) {
        Post post = postRepository.getReferenceById(postId);
        Member member = memberRepository.getReferenceById(memberId);

       Like like = likeRepository.findByPostAndMember(post, member)
                .orElseThrow(() -> new IllegalArgumentException("likes not exist"));

        likeRepository.delete(like);
        Long likeCount = likeRepository.countByPost(post);
        return new LikeOutputDto(member, post, likeCount);
    }

}
