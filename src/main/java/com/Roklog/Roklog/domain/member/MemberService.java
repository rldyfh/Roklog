package com.Roklog.Roklog.domain.member;


import com.Roklog.Roklog.web.member.request.MemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void enroll(MemberRequest memberRequest) {
        Member member = new Member(memberRequest.getLoginId(), memberRequest.getPassword());

        memberRepository.save(member);
    }

}
