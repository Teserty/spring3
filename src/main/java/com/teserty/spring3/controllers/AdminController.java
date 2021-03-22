package com.teserty.spring3.controllers;

import com.teserty.spring3.enities.Item;
import com.teserty.spring3.enities.Shop;
import com.teserty.spring3.enities.User;
import com.teserty.spring3.enities.dto.Response;
import com.teserty.spring3.services.FeedbackService;
import com.teserty.spring3.services.ItemService;
import com.teserty.spring3.services.ShopsService;
import com.teserty.spring3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    public AdminController(UserService userService, ShopsService shopsService, ItemService itemService) {
        this.userService = userService;
        this.shopsService = shopsService;
        this.itemService = itemService;
    }
    private final UserService userService;
    private final ShopsService shopsService;
    private final ItemService itemService;
    @GetMapping("/admin")
    public List<User> userList() {
        return userService.allUsers();
    }

    @PostMapping("/admin")
    public Response adminUsers(@RequestParam(required = true, defaultValue = "" ) Long userId,
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

    @PostMapping("/admin/create-item/{id}")
    public Response createItem(@PathVariable Integer id){
        for (int i = 0; i < id; i++)
            itemService.createNewItem(Item.builder()
                    .name("Test Object "+i)
                    .description("Test Object " + " description")
                    .price((i+1)*100)
                    .build());
        return new Response("", "");
    }
    @PostMapping("/admin/create-shops/{id}")
    public Response createShops(@PathVariable Integer id){
        for (int i = 0; i < id; i++)
            shopsService.createNewShop(
                    Shop.builder()
                    .build()
            );
        return new Response("", "");
    }
}