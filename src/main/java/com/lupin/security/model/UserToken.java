package com.lupin.security.model;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

public class UserToken implements Serializable {
    private String username;
    private String token;
    private Collection<? extends GrantedAuthority> authorities;

    public UserToken(String username, String token, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.token = token;
        this.authorities = authorities;
    }

    public UserToken() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
