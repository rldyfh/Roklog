package com.Roklog.Roklog.web.post;

import com.Roklog.Roklog.domain.post.Post;
import com.Roklog.Roklog.web.member.request.MemberResponse;
import com.Roklog.Roklog.web.post.request.PostCreate;
import com.Roklog.Roklog.domain.post.PostService;
import com.Roklog.Roklog.web.post.request.PostEdit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request) {
        List<Post> posts = postService.getPosts();
        model.addAttribute("posts", posts);

        HttpSession session = request.getSession(false);

        if(session == null) return "home";


        Object loginMember = session.getAttribute("loginMember");
        if(loginMember == null) return "home";

        model.addAttribute("member", loginMember);

        return "loginHome";

    }

    @PostMapping("/delete/{postId}")
    public String delete(@PathVariable Long postId) {
        log.info("id={}", postId);
        postService.delete(postId);
        return "redirect:/";
    }

    @GetMapping("/post")
    public String postForm(@ModelAttribute("post") PostCreate postCreate) {
        return "post/addPostForm";
    }

    @PostMapping("/post")
    public String post(@Validated @ModelAttribute("post") PostCreate post, BindingResult bindingResult) {

        if(!StringUtils.hasText(post.getTitle()) || !StringUtils.hasText(post.getContent())) {
            bindingResult.reject("hasError", "제목과 내용 둘 다 입력해주세요.");
        }

        if(bindingResult.hasErrors()) {
            log.info("error={}", bindingResult);
            return "post/addPostForm";
        }

        postService.write(new PostCreate(post.getTitle(), post.getContent()));
        return "redirect:/";

    }

    @GetMapping("/post/{postId}")
    public String getOne(@PathVariable String postId, Model model) {
        Post post = postService.findById(Long.valueOf(postId));
        model.addAttribute("post", post);
        return "post/post";
    }

    @GetMapping("/post/{postId}/edit")
    public String editForm(@PathVariable Long postId, Model model) {
        Post post = postService.findById(Long.valueOf(postId));
        model.addAttribute("post", post);
        return "post/editPostForm";
    }

    @PostMapping("/post/{postId}/edit")
    public String editPost(@PathVariable Long postId, @Validated @ModelAttribute("post") PostEdit postEdit, BindingResult bindingResult) {
        if(!StringUtils.hasText(postEdit.getTitle()) || !StringUtils.hasText(postEdit.getContent())) {
            bindingResult.reject(null, "수정 시 제목과 내용은 필수입니다.");
        }

        if(bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "post/editPostForm";
        }

        //성공 로직
        Post post = new Post();
        post.setTitle(postEdit.getTitle());
        post.setContent(postEdit.getContent());
        postService.update(postId, post);

        return "redirect:/post/{postId}";

    }
}
