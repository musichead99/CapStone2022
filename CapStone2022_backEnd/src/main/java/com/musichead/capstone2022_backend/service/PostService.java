package com.musichead.capstone2022_backend.service;

import com.musichead.capstone2022_backend.domain.board.Board;
import com.musichead.capstone2022_backend.domain.board.BoardRepository;
import com.musichead.capstone2022_backend.domain.post.Post;
import com.musichead.capstone2022_backend.domain.post.PostRepository;
import com.musichead.capstone2022_backend.domain.member.Member;
import com.musichead.capstone2022_backend.domain.member.MemberRepository;
import com.musichead.capstone2022_backend.domain.subscribe.SubscribeRepository;
import com.musichead.capstone2022_backend.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final SubscribeRepository subscribeRepository;

    public Page<Post> findAll(int offset, int size) {
        PageRequest pageRequest = PageRequest.of(offset, size);
        return postRepository.findAll(pageRequest);
    }

    public Post findById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        Post post = null;

        if(optionalPost.isPresent()) {
            post = optionalPost.get();
        }

        return post;
    }

    public Page<Post> findByBoardId(Board board, int offset, int size) {
        PageRequest pageRequest = PageRequest.of(offset, size);
        return postRepository.findByBoard(board, pageRequest);
    }

    public List<Post> findSubscriberPosts(Long memberId) {
        return postRepository.findSubscriberPostsByMemberId(memberId);
    }

    public Post save(PostDto postDto) {
        Member member = memberRepository.getReferenceById(postDto.getMemberId());
        Board board = boardRepository.getReferenceById(postDto.getBoardId());
        Post post = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .member(member)
                .board(board)
                .build();

        return postRepository.save(post);
    }

    @Transactional
    public Post update(Long id, PostDto postDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id=" + id + " post not exist"));

        post.update(postDto.getTitle(), postDto.getContent());
        return post;
    }

    public Post delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id=" + id + " post not exist"));

        postRepository.delete(post);

        return post;
    }
}
