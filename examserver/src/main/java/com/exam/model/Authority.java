package com.exam.model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {
    private String authority;

    public String getAuthority() {

        return authority;
    }

    public void setAuthority(String authority) {

        this.authority = authority;
    }

    public Authority() {
    }

    public Authority(String authority) {
        this.authority = authority;
    }
}

