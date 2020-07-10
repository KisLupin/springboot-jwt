package com.lupin.security.service;

import com.lupin.security.inter.UserTokenService;
import com.lupin.security.model.UserToken;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserTokenAccessServiceImpl implements UserTokenService {

    private RedisTemplate<String, UserToken> redisTemplate;

    private HashOperations hashOperations;


    public UserTokenAccessServiceImpl(RedisTemplate<String, UserToken> redisTemplate) {
        this.redisTemplate = redisTemplate;

        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(UserToken user) {
        hashOperations.put("NameAndToken", user.getUsername(), user.getToken());
    }

    @Override
    public Map<String, UserToken> findAll() {
        return hashOperations.entries("NameAndToken");
    }

    @Override
    public UserToken findById(String name) {
        return (UserToken)hashOperations.get("NameAndToken", name);
    }

    @Override
    public void update(UserToken user) {
        save(user);
    }

    @Override
    public void delete(String name) {

        hashOperations.delete("NameAndToken", name);
    }
}
