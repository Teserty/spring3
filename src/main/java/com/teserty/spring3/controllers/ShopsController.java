package com.teserty.spring3.controllers;

import com.teserty.spring3.enities.dto.ShopDTO;
import com.teserty.spring3.services.ShopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ShopsController {
    private final ShopsService shopsService;
    @Autowired
    public ShopsController(ShopsService shopsService) {
        this.shopsService = shopsService;
    }
    @GetMapping("/shops/shop/{id}")
    public ShopDTO getShopById(@PathVariable Long id){
        return shopsService.findShopById(id);
    }
    @GetMapping("/shops/{page}")
    public List<ShopDTO> getShopsPages(@PathVariable int page){
        Pageable pageable = PageRequest.of(page, 20);
        return shopsService.findAll(pageable);
    }
    @GetMapping("/shops/shop/{name}")
    public ShopDTO getShopByName(@PathVariable String name){
        return shopsService.findByName(name);
    }
}
