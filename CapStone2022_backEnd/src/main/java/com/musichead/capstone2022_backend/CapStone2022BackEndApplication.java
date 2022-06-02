package com.musichead.capstone2022_backend;

import com.musichead.capstone2022_backend.domain.board.Board;
import com.musichead.capstone2022_backend.domain.board.BoardRepository;
import com.musichead.capstone2022_backend.domain.comment.Comment;
import com.musichead.capstone2022_backend.domain.comment.CommentRepository;
import com.musichead.capstone2022_backend.domain.like.Like;
import com.musichead.capstone2022_backend.domain.like.LikeRepository;
import com.musichead.capstone2022_backend.domain.member.Role;
import com.musichead.capstone2022_backend.domain.post.Post;
import com.musichead.capstone2022_backend.domain.post.PostRepository;
import com.musichead.capstone2022_backend.domain.member.Member;
import com.musichead.capstone2022_backend.domain.member.MemberRepository;
import com.musichead.capstone2022_backend.domain.subscribe.Subscribe;
import com.musichead.capstone2022_backend.domain.subscribe.SubscribeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableConfigurationProperties(AppProperties.class)
@EnableJpaAuditing
@SpringBootApplication
public class CapStone2022BackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(CapStone2022BackEndApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(MemberRepository memberRepository, BoardRepository boardRepository, PostRepository postRepository, CommentRepository commentRepository, SubscribeRepository subscribeRepository, LikeRepository likeRepository) throws Exception {
        return (args) -> {
            Board board = new Board().builder().name("전체").build();
            Member member = new Member().builder().email("musichead99@naver.com").name("정성구").picture(null).role(Role.ADMIN).build();
            Member member2 = new Member().builder().email("rdd0426@naver.com").name("김영우").picture(null).role(Role.ADMIN).build();
            Post post = new Post().builder().title("테스트글").content("테스트내용").board(board).member(member).build();
            Subscribe subscribe = new Subscribe(member, member2);
            boardRepository.save(board);
            memberRepository.save(member);
            memberRepository.save(member2);
            postRepository.save(post);
            subscribeRepository.save(subscribe);

            for(int i = 1; i <= 10; i++) {
                commentRepository.save(new Comment().builder().post(post).member(member).content("테스트댓글 "+i).build());
                postRepository.save(new Post().builder().board(board).title("테스트글").content("테스트내용").member(member).build());
                postRepository.save(new Post().builder().board(board).title("테스트글 2").content("테스트내용 2").member(member2).build());
                Member testMember = new Member().builder().email("test" + i + "@naver.com").name("테스트유저" + i).picture(null).build();
                memberRepository.save(testMember);
                likeRepository.save(new Like().builder().post(post).member(testMember).build());
            }
        };
    }
}
