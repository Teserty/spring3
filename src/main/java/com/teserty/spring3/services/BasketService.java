package com.teserty.spring3.services;

import com.teserty.spring3.dto.BasketDto;
import com.teserty.spring3.entity.Basket;

public interface BasketService {
    Basket getBasketByUsername(String token);
    BasketDto getBasketDtoByUsername(String token);

    void save(Basket basket);

    BasketDto convert(Basket basket);
}
