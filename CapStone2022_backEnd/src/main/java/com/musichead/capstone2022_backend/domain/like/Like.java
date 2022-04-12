package com.musichead.capstone2022_backend.domain.like;

import com.musichead.capstone2022_backend.domain.member.Member;
import com.musichead.capstone2022_backend.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Table(
        name = "like_table",
        uniqueConstraints = @UniqueConstraint(columnNames = {"post_id", "member_id"})
)
@Entity
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public Like(Post post, Member member) {
        this.post = post;
        this.member = member;
    }
}
