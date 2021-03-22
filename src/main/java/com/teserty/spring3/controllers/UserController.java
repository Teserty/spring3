package com.teserty.spring3.controllers;

import com.teserty.spring3.dto.Response;
import com.teserty.spring3.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImp userService;
    @Autowired
    public UserController(UserServiceImp userService) {
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