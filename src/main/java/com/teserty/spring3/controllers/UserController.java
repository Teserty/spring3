package com.teserty.spring3.controllers;

import com.teserty.spring3.enities.dto.Response;
import com.teserty.spring3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public Response addUser(@RequestParam String username, @RequestParam String password) {
        if (!userService.isUserWithUsernameExist(username)){
            userService.createUser(username, password);
            return new Response("200", "Пользователь создан");
        }else{
            return new Response("400", "Пользователь c этим именем уже существует");
        }
    }
}