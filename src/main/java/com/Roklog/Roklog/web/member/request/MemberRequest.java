package com.Roklog.Roklog.web.member.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MemberRequest {

    @NotBlank
    public String loginId;

    @NotBlank
    public String password;


}
