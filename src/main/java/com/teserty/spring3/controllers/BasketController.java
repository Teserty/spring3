package com.teserty.spring3.controllers;


import com.teserty.spring3.dto.BasketDto;
import com.teserty.spring3.entity.Basket;
import com.teserty.spring3.entity.Item;
import com.teserty.spring3.exceptions.ItemNotFoundException;
import com.teserty.spring3.services.BasketService;
import com.teserty.spring3.services.ItemService;
import com.teserty.spring3.services.ItemServiceImp;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/basket")
public class BasketController {
    private final BasketService basketService;
    private final ItemServiceImp itemService;
    public BasketController(BasketService basketService, ItemServiceImp itemService) {
        this.basketService = basketService;
        this.itemService = itemService;
    }

    @PostMapping("/remove")
    public BasketDto removeItemFromBasket(@RequestParam int index, @RequestParam String token){
        Basket basket = basketService.getBasketByUsername(token);
        basket.getItemsList().remove(index);
        basketService.save(basket);
        return basketService.convert(basket);
    }
    @PostMapping("/add")
    public BasketDto addItemToBasket(@RequestParam String id, @RequestParam String token){
        Basket basket = basketService.getBasketByUsername(token);
        Optional<Item> item = itemService.getById(Long.valueOf(id));
        if (item.isPresent()) {
            basket.getItemsList().add(item.get());
            basketService.save(basket);
            return basketService.convert(basket);
        }else{
            throw new ItemNotFoundException();
        }
    }
    @GetMapping("/")
    public BasketDto showBasket(@RequestParam String token){
        return basketService.getBasketDtoByUsername(token);
    }
}
