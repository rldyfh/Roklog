package com.Roklog.Roklog.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemoryMemberRepository implements MemberRepository{

    private final EntityManager em;

    @Transactional
    public void save(Member member) {
        em.persist(member);
    }

    public List<Member> findByLoginId(String loginId, String password) {
        String query = "select m from Member m where m.loginId =: loginId and m.password =: password";
        return em.createQuery(query, Member.class)
                .setParameter("loginId", loginId)
                .setParameter("password", password)
                .getResultList();
    }
}
