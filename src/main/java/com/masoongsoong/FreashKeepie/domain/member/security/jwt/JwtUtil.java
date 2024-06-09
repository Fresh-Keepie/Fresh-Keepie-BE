package com.masoongsoong.FreashKeepie.domain.member.security.jwt;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    // Header에 들어갈 Key값(인증값)
    public static final String AUTHORIZATION_HEADER = "Authorization";

    // 사용자 권한 값
    public static final String AUTHORIZATION_KEY = "auth";

    // 토큰 식별값
    public static final String BEARER_PREFIX = "Bearer ";

    // 토큰 만료시간
    private final long ACCESS_TOKEN_TIME = 60 * 60 * 1000L; // 1시간

    //@Va
}
