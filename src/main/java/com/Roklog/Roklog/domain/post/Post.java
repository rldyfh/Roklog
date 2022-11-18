package com.Roklog.Roklog.domain.post;

import com.Roklog.Roklog.domain.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    private Post(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

}
