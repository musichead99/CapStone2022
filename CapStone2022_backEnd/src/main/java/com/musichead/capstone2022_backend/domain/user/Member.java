package com.musichead.capstone2022_backend.domain.user;

import com.musichead.capstone2022_backend.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String picture;
    private String name;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Post> posts;

    @Builder
    public Member(String email, String picture, String name) {
        this.email = email;
        this.picture = picture;
        this.name = name;
    }
}
