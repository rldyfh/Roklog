package com.Roklog.Roklog.web.post.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class PostCreate {

    @NotBlank
    private String title;
    @NotBlank
    private String content;

    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
