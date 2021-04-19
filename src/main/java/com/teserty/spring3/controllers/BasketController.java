package com.teserty.spring3.controllers;


import com.teserty.spring3.dto.BasketDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/basket/")
public class BasketController {
    @PostMapping("/remove")
    public BasketDto removeItemFromBasket(){
        return null;
    }
}
