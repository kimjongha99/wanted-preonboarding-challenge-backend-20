package com.example.wantedchallenge.domain.enums;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;


@Getter
public enum UserRoleEnum {
    USER(Authority.USER, "유저"),  // 사용자 권한
    ADMIN(Authority.ADMIN, "마스터");    // 마스터 권한

    private final String authority;
    private final String description;

    UserRoleEnum(String authority, String description) {
        this.authority = authority;
        this.description = description;
    }

    public static class Authority {
        public static final String USER = "ROLE_USER";  // 유저
        public static final String ADMIN = "ROLE_ADMIN";      // 마스터 계정
    }
}