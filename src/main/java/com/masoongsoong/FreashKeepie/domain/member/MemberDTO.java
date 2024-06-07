package com.masoongsoong.FreashKeepie.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private String userId;
    private String nickname;
    private String nowPassword;
    private String newPassword;
    private String newPasswordCheck;

    public static MemberDTO of(User user){
        return MemberDTO.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .build();
    }
}
