package com.musichead.capstone2022_backend.domain.post;

import com.musichead.capstone2022_backend.domain.BaseTimeEntity;
import com.musichead.capstone2022_backend.domain.board.Board;
import com.musichead.capstone2022_backend.domain.comment.Comment;
import com.musichead.capstone2022_backend.domain.like.Like;
import com.musichead.capstone2022_backend.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private String audio;

    private String realAudio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id", nullable = false)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Like> likes;

    @Builder
    public Post(String title, String content, Board board, Member member) {
        this.title = title;
        this.content = content;
        this.board = board;
        this.member = member;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateAudio(String audio, String realAudio) {
        this.audio = audio;
        this.realAudio = realAudio;
    }
}
