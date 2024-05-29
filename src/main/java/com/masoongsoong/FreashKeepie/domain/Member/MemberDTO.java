package com.masoongsoong.FreashKeepie.domain.Member;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberDTO {

    private String loginID;
    private String nickname;
    private String nowPassword;
    private String newPassword;
    private String newPasswordCheck;

    public static MemberDTO of(Member member){
        return MemberDTO.builder()
                .loginID(member.getLoginID())
                .nickname(member.getNickname())
                .build();
    }
}
