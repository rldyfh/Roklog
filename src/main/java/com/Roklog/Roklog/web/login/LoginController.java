package com.Roklog.Roklog.web.login;


import com.Roklog.Roklog.domain.login.LoginService;
import com.Roklog.Roklog.domain.member.MemberService;
import com.Roklog.Roklog.web.member.request.MemberRequest;
import com.Roklog.Roklog.web.member.request.MemberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("member", new MemberRequest());
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("member") MemberRequest member, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        MemberResponse loginMember = loginService.login(member.getLoginId(), member.getPassword());

        if(loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("loginMember", loginMember);

        return "redirect:" + redirectURL;
    }



    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);

        if(httpSession != null) {
            httpSession.invalidate();
        }
        return "redirect:/";
    }
}
