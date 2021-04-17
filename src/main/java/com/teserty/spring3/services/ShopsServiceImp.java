package com.teserty.spring3.services;

import com.teserty.spring3.conventor.Converter;
import com.teserty.spring3.dto.ShopDto;
import com.teserty.spring3.entity.Shop;
import com.teserty.spring3.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopsServiceImp implements ShopService {
    private final ShopRepository shopRepository;
    private final Converter converter;
    @Autowired
    public ShopsServiceImp(ShopRepository shopRepository, Converter converter) {
        this.shopRepository = shopRepository;
        this.converter = converter;
    }

    public ShopDto findByName(String name) {
        return converter.convertToDto(shopRepository.findByName(name));
    }

    public ShopDto findById(Long id) {
        return converter.convertToDto(shopRepository.findById(id).get());
    }

    public List<ShopDto> findAll(Pageable pageable) {
        return shopRepository.findAll(pageable).stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
    }

    public void createNewShop(Shop shop) {
        shopRepository.save(shop);
    }
}
