package com.Roklog.Roklog.web.member;


import com.Roklog.Roklog.domain.member.MemberService;
import com.Roklog.Roklog.web.member.request.MemberRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member")
    public String saveForm(Model model) {
        model.addAttribute("member", new MemberRequest());
        return "member/addMember";
    }


    @PostMapping("/member")
    public String saveMember(@Validated @ModelAttribute("member") MemberRequest memberRequest, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            log.error("errors={}", bindingResult);
            return "member/addMember";
        }

        memberService.enroll(memberRequest);

        return "redirect:/";
    }

}
