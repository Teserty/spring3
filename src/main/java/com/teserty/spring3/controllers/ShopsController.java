package com.teserty.spring3.controllers;

import com.teserty.spring3.enities.Shop;
import com.teserty.spring3.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ShopsController {
    private ShopRepository shopRepository;
    @Autowired
    public void setShopRepository(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }
    @GetMapping("/shops/shop/{id}")
    public Shop getShopById(@PathVariable Long id){
        return shopRepository.findShopById(id);
    }
    @GetMapping("/shops/{page}")
    public List<Shop> getShopsPages(@PathVariable int page){
        Pageable pageable = PageRequest.of(page, 20);
        return shopRepository.findAll(pageable);
    }
    @GetMapping("/shops/{name}")
    public Shop getShopByName(@PathVariable String name){
        return shopRepository.findByName(name);
    }
}
