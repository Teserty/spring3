package com.teserty.spring3.controllers;

import com.teserty.spring3.enities.User;
import com.teserty.spring3.enities.dto.Response;
import com.teserty.spring3.services.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/registration")
    public Response addUser(@ModelAttribute("userForm") @Valid User userForm) {
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            return new Response("400", "Пароли не совпадают");
        }
        if (!userService.saveUser(userForm)){
            return new Response("400", "Пользователь с таким именем уже существует");
        }
        return new Response("200", "Пользователь создан");
    }
}