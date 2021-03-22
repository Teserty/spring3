package com.teserty.spring3.services;

import com.teserty.spring3.dto.ShopDto;
import com.teserty.spring3.enities.Shop;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShopService {
    void createNewShop(Shop shop);
    public List<ShopDto> findAll(Pageable pageable);
    public ShopDto findById(Long id);
    public ShopDto findByName(String name);
}
