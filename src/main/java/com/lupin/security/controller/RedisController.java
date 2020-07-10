package com.lupin.security.controller;

import com.lupin.security.inter.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private UserTokenService userTokenService;

    @GetMapping("/all")
    public RequestEntity<?> allUserToken(){
        return new RequestEntity<>(userTokenService.findAll(), HttpMethod.GET,null);
    }
}
