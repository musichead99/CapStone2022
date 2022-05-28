package com.musichead.capstone2022_backend.domain.member;

import com.musichead.capstone2022_backend.domain.like.Like;
import com.musichead.capstone2022_backend.domain.post.Post;
import com.musichead.capstone2022_backend.domain.subscribe.Subscribe;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String picture;

    @Column(nullable = false)
    private String name;

    private String article;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Post> posts;

//    @OneToMany(mappedBy = "fromMember", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Subscribe> subscribes;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Like> likes;

    @Builder
    public Member(String email, String picture, String name, String article) {
        this.email = email;
        this.picture = picture;
        this.name = name;
        this.article = article;
    }

    public void update(String name, String article) {
        this.name = name;
        this.article = article;
    }
}
