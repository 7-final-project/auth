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
        public static final String CUSTOMER = "ROLE_CUSTOMER";
        public static final String OWNER = "ROLE_OWNER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}

