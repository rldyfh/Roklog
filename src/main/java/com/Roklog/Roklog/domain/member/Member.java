package com.Roklog.Roklog.domain.member;

import com.Roklog.Roklog.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;

    private String password;

    @OneToMany(mappedBy = "member")
    private final List<Post> posts = new ArrayList<>();

    public Member(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }


}
