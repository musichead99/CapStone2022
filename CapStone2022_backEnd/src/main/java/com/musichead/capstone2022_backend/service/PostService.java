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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostService {

    String path = System.getProperty("user.dir") + File.separator + "audio" + File.separator;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public Post findById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        Post post = null;

        if(optionalPost.isPresent()) {
            post = optionalPost.get();
        }

        return post;
    }

    public List<Post> findByBoardId(Board board, int offset, int size) {
        PageRequest pageRequest = PageRequest.of(offset, size);
        return postRepository.findByBoard(board, pageRequest);
    }

    public List<Post> findSubscriberPosts(Long memberId, int offset, int size) {
        PageRequest pageRequest = PageRequest.of(offset, size);
        return postRepository.findSubscriberPostsByMemberId(memberId, pageRequest);
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

    @Transactional
    public String update(long id, MultipartFile audio) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id=" + id + " post not exist"));

        /* 이미 오디오 파일이 존재하면 삭제 */
        if(post.getRealAudio() != null) {
            deleteFile(post.getRealAudio());
        }

        /* 파일 이름과 실제 저장할 파일명을 생성 */
        UUID uuid = UUID.randomUUID();
        String audioName = audio.getOriginalFilename();
        String realAudioName = uuid.toString() + "_" + audioName;

        /* 오디오 파일 저장 */
        try (FileOutputStream fos = new FileOutputStream(path + realAudioName);
             InputStream is = audio.getInputStream()) {


            int readCount = 0;
            byte[] buffer = new byte[1024];

            while ((readCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, readCount);
            }
        } catch (Exception ex) {
            throw new RuntimeException("file Save Error");
        }

        post.updateAudio(audioName, realAudioName);

        return audioName;
    }

    @Transactional
    public Post delete(Long id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id=" + id + " post not exist"));

        if(post.getRealAudio() != null) {
            deleteFile(post.getRealAudio());
        }

        postRepository.delete(post);

        return post;
    }

    private void deleteFile(String fileName) {
        File file = new File(path + fileName);
        if(file.exists()) {
            file.delete();
        } else {
            throw new IllegalArgumentException(fileName + " dose not exist");
        }
    }
}
