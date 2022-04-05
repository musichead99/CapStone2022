package com.musichead.capstone2022_backend.domain.subscribe;

import com.musichead.capstone2022_backend.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"from_member_id", "to_member_id"})
)
@Entity
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_member_id" , nullable = false)
    private Member fromMember;

    @ManyToOne
    @JoinColumn(name = "to_member_id", nullable = false)
    private Member toMember;

    @Builder
    public Subscribe(Member fromMember, Member toMember) {
        this.fromMember = fromMember;
        this.toMember = toMember;
    }
}
