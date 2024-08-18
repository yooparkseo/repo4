package com.chami.vo;

public enum Role {
    USER("ROLE_USER", "일반사용자"),
    ADMIN("ROLE_ADMIN", "일반관리자");

    private final String role;
    private final String desc;

    Role(String role, String desc) {
        this.role = role;
        this.desc = desc;
    }

    public String getRole() {
        return role;
    }

    public String getDesc() {
        return desc;
    }

    public String getRoleWithoutPrefix() {
        return role.replace("ROLE_", "");
    }
}
