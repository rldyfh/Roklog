package com.Roklog.Roklog.domain.login;


import com.Roklog.Roklog.domain.member.Member;
import com.Roklog.Roklog.domain.member.MemoryMemberRepository;
import com.Roklog.Roklog.web.member.request.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemoryMemberRepository memoryMemberRepository;

    public Member login(String loginId, String password) {

        List<Member> loginMember = memoryMemberRepository.findByLoginId(loginId, password);

        if(loginMember.size() == 0) return null;

        return loginMember.get(0);
    }
}
