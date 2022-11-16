package com.Roklog.Roklog.domain.post;

import com.Roklog.Roklog.web.post.request.PostCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Long write(PostCreate postCreate) {

        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();

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
