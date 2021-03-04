package com.teserty.spring3.controllers;

import com.teserty.spring3.enities.User;
import com.teserty.spring3.enities.dto.Response;
import com.teserty.spring3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public List<User> userList() {
        return userService.allUsers();
    }

    @PostMapping("/admin")
    public Response deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                               @RequestParam(required = true, defaultValue = "" ) String action) {
        switch (action){
            case "delete":{
                userService.deleteUser(userId);
                return new Response("200", "Пользователь удален");
            }
            case "lock":{
                userService.setUserIsLocked(userId, true);
                return new Response("200", "Пользователь заблокирован");
            }
            case "unlock":{
                userService.setUserIsLocked(userId, false);
                return new Response("200", "Пользователь разблокирован");
            }
        }
        return new Response("400", "Действие не найдено");
    }

    @GetMapping("/admin/gt/{userId}")
    public List<User> gtUser(@PathVariable("userId") Long userId) {
        return userService.usergtList(userId);
    }
}