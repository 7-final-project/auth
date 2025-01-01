package com.qring.auth.domain.model.constraint;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
    USER(Authority.CUSTOMER),
    OWNER(Authority.OWNER),
    ADMIN(Authority.ADMIN);

    private final String authority;

    public static class Authority {
        public static final String CUSTOMER = "고객";
        public static final String OWNER = "점주";
        public static final String ADMIN = "관리자";
    }

    public static RoleType fromString(String authority) {
        return switch (authority) {
            case Authority.CUSTOMER -> RoleType.USER;
            case Authority.OWNER -> RoleType.OWNER;
            case Authority.ADMIN -> RoleType.ADMIN;
            default -> throw new IllegalArgumentException("유효하지 않은 타입입니다.");
        };
    }
}

