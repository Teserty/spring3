package com.teserty.spring3.services;

import com.teserty.spring3.enities.Shop;
import com.teserty.spring3.enities.dto.ShopDTO;
import com.teserty.spring3.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopsService {
    private final DTOService dtoService;
    private ShopRepository shopRepository;
    @Autowired
    public ShopsService(DTOService dtoService) {
        this.dtoService = dtoService;
    }
    @Autowired
    public void setShopRepository(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public ShopDTO findByName(String name) {
        return dtoService.convertToDto(shopRepository.findByName(name));
    }

    public ShopDTO findShopById(Long id) {
        return dtoService.convertToDto(shopRepository.findShopById(id));
    }

    public List<ShopDTO> findAll(Pageable pageable) {
        return shopRepository.findAll(pageable).stream()
                .map(dtoService::convertToDto)
                .collect(Collectors.toList());
    }

    public void createNewShop(Shop build) {
    }
}
