package com.teserty.spring3.controllers;

import com.teserty.spring3.conventor.Converter;
import com.teserty.spring3.dto.ItemDto;
import com.teserty.spring3.dto.ShopDto;
import com.teserty.spring3.enities.Item;
import com.teserty.spring3.enities.Shop;
import com.teserty.spring3.enities.User;
import com.teserty.spring3.dto.Response;
import com.teserty.spring3.services.ItemServiceImp;
import com.teserty.spring3.services.ShopsServiceImp;
import com.teserty.spring3.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    public AdminController(UserServiceImp userService, ShopsServiceImp shopsService, ItemServiceImp itemService, Converter converter) {
        this.userService = userService;
        this.shopsService = shopsService;
        this.itemService = itemService;
        this.converter = converter;
    }
    private final UserServiceImp userService;
    private final ShopsServiceImp shopsService;
    private final ItemServiceImp itemService;
    private final Converter converter;
    @GetMapping("/")
    public List<User> userList() {
        return userService.allUsers();
    }

    @PostMapping("/")
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

    @PostMapping("/create-items")
    public Response createItems(@RequestBody ItemDto item){
        itemService.createNewItem(
                Item.builder()
                    .name(item.getName())
                    .description(item.getDescription())
                    .price(new BigDecimal(item.getPrice()))
                    .build());
        return new Response("", "");
    }
    @PostMapping("/create-shops")
    public Response createShops(@RequestBody ShopDto shopDto){
        shopsService.createNewShop(
                Shop.builder()
                        .name(shopDto.getName())
                        .description(shopDto.getDescription())
                        .build());
        return new Response("", "");
    }
}