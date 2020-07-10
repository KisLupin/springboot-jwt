package com.lupin.security.inter;

import com.lupin.security.model.UserToken;

import java.util.Map;

public interface UserTokenService {
    void save(UserToken user);
    Map<String, UserToken> findAll();
    UserToken findById(String name);
    void update(UserToken user);
    void delete(String name);
}
