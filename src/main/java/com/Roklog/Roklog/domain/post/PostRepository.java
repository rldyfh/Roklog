package com.Roklog.Roklog.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    @PersistenceContext
    private final EntityManager em;

    @Transactional
    public Long save(Post post) {
        em.persist(post);
        return post.getId();
    }

    public Post find(Long id) {
        return em.find(Post.class, id);
    }

    public List<Post> findAll() {
        String query = "select p from Post p order by p.id desc";
        return em.createQuery(query, Post.class).getResultList();
    }

    @Transactional
    public void update(Long postId, Post post) {
        Post findOne = find(postId);
        findOne.setTitle(post.getTitle());
        findOne.setContent(post.getContent());
        em.merge(findOne);
    }

    @Transactional
    public void remove(Long postId) {
        em.remove(find(postId));
    }

}
