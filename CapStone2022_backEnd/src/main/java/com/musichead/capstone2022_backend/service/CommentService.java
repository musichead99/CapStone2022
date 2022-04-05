package com.musichead.capstone2022_backend.service;

import com.musichead.capstone2022_backend.domain.comment.Comment;
import com.musichead.capstone2022_backend.domain.comment.CommentRepository;
import com.musichead.capstone2022_backend.domain.post.Post;
import com.musichead.capstone2022_backend.domain.post.PostRepository;
import com.musichead.capstone2022_backend.domain.member.Member;
import com.musichead.capstone2022_backend.domain.member.MemberRepository;
import com.musichead.capstone2022_backend.dto.CommentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public List<Comment> findByPostId(Post post) {
        Long post_id = post.getId();
        return commentRepository.findByPost_id(post_id);
    }

    public Comment save(CommentDto commentDto) {
        Post post = postRepository.getReferenceById(commentDto.getPostId());
        Member member = memberRepository.getReferenceById(commentDto.getMemberId());

        Comment comment = Comment.builder()
                .post(post)
                .member(member)
                .content(commentDto.getContent())
                .build();

        return commentRepository.save(comment);
    }

    @Transactional
    public Comment update(Long id, CommentDto commentDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id=" + id + " is not exist"));

        comment.update(commentDto.getContent());
        return comment;
    }

    public Comment delete(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id=" + id + " post not exist"));

        commentRepository.delete(comment);
        return comment;
    }
}
