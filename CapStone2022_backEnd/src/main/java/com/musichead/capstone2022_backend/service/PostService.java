package com.musichead.capstone2022_backend.service;

import com.musichead.capstone2022_backend.domain.board.Board;
import com.musichead.capstone2022_backend.domain.board.BoardRepository;
import com.musichead.capstone2022_backend.domain.post.Post;
import com.musichead.capstone2022_backend.domain.post.PostRepository;
import com.musichead.capstone2022_backend.domain.user.Member;
import com.musichead.capstone2022_backend.domain.user.MemberRepository;
import com.musichead.capstone2022_backend.dto.PostDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        Post post = null;

        if(optionalPost.isPresent()) {
            post = optionalPost.get();
        }

        return post;
    }

    public Post save(PostDto postDto) {
        Member member = memberRepository.getReferenceById(postDto.getMemberId());
        Board board = boardRepository.getReferenceById(postDto.getBoardId());
        Post post = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .member(member)
                .board(board)
                .createdAt(LocalDateTime.now())
                .build();

        return postRepository.save(post);
    }

    public Post delete(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        Post post = null;

        if(optionalPost.isPresent()) {
            post = optionalPost.get();
        }

        return post;
    }

    public List<Post> findByMember_id(Member member) {
        Long member_id = member.getId();
        return postRepository.findByMember_id(member_id);
    }

    public List<Post> findByBoard_id(Board board) {
        Long board_id = board.getId();
        return postRepository.findByBoard_id(board_id);
    }
}
