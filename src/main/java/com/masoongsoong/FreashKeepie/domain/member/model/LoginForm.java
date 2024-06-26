package com.masoongsoong.FreashKeepie.domain.member.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {
    @NotEmpty(message = "아이디를 입력해주세요")
    private String userId;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;

}
