package com.masoongsoong.FreashKeepie.domain.member;

import com.masoongsoong.FreashKeepie.domain.member.model.User;
import com.masoongsoong.FreashKeepie.domain.member.model.UserRole;
import lombok.Getter;

import java.util.List;

@Getter
public class UserDto {
    private Long id;
    private String userId;
    private String nickname;
    private String birth;
    private String email;
    private List<UserRole> roles;

    public UserDto(User user){
        this.id = user.getId();
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
        this.birth = user.getBirth();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }
}
