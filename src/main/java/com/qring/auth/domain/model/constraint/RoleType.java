package com.qring.auth.domain.model.constraint;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
    USER(Authority.CUSTOMER), // 사용자 권한
    OWNER(Authority.OWNER), // 사장 권한
    ADMIN(Authority.ADMIN); // 관리자 권한

    private final String authority;

    public static class Authority {
        public static final String CUSTOMER = "고객";
        public static final String OWNER = "점주";
        public static final String ADMIN = "관리자";
    }
}

