package com.Roklog.Roklog.domain.login;


import com.Roklog.Roklog.domain.member.Member;
import com.Roklog.Roklog.domain.member.MemberRepository;
import com.Roklog.Roklog.web.member.request.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public MemberResponse login(String loginId, String password) {

        List<Member> loginMember = memberRepository.findByLoginId(loginId, password);

        if(loginMember.size() == 0) return null;

        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setLoginId(loginMember.get(0).getLoginId());

        return memberResponse;
    }
}
