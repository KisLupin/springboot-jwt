package com.lupin.security.controller;

import com.lupin.security.inter.UserService;
import com.lupin.security.model.User;
import com.lupin.security.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService UserServiceImpl;

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user){
        UserServiceImpl.addNewUser(user);
        return "add user success";
    }
}
