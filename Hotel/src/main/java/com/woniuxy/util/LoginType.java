package com.woniuxy.util;

public enum LoginType {
	// 普通用户登录    管理员登录
	 USER("User"),  ADMIN("Admin");

    private String type;

    private LoginType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type.toString();
    }

}
