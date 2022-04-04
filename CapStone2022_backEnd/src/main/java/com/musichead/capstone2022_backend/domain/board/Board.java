package com.musichead.capstone2022_backend.domain.board;

import com.musichead.capstone2022_backend.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy="board", fetch = FetchType.LAZY)
    private List<Post> Posts;

    @Builder
    public Board(String name) {
        this.name = name;
    }
}
