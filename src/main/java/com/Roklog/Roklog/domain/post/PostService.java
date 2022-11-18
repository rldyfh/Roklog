package com.Roklog.Roklog.domain.post;

import com.Roklog.Roklog.domain.member.Member;
import com.Roklog.Roklog.domain.member.MemberRepository;
import com.Roklog.Roklog.web.member.request.MemberResponse;
import com.Roklog.Roklog.web.post.request.PostCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public Long write(PostCreate postCreate, Member loginMember) {

        Post post = new Post();
        post.setTitle(postCreate.getTitle());
        post.setContent(postCreate.getContent());
        post.setMember(loginMember);

        return postRepository.save(post);
    }

    public Post findById(Long id) {
        return postRepository.find(id);
    }

    public void update(Long postId, Post post) {
        postRepository.update(postId, post);
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public void delete(Long id) {
        postRepository.remove(id);
    }
}
