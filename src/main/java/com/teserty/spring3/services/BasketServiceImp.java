package com.teserty.spring3.services;

import com.teserty.spring3.conventor.Converter;
import com.teserty.spring3.dto.BasketDto;
import com.teserty.spring3.entity.Basket;
import com.teserty.spring3.repositories.BasketRepository;
import org.springframework.stereotype.Component;

@Component
public class BasketServiceImp implements BasketService{
    private BasketRepository basketRepository;
    private final Converter converter;

    public BasketServiceImp(Converter converter) {
        this.converter = converter;
    }

    @Override
    public Basket getBasketByUsername(String token) {
        return basketRepository.findByOwner_Username(token);
    }
    @Override
    public BasketDto getBasketDtoByUsername(String token) {
        return converter.convertToDto(basketRepository.findByOwner_Username(token));
    }

    @Override
    public void save(Basket basket) {
        basketRepository.save(basket);
    }

    @Override
    public BasketDto convert(Basket basket) {
        return converter.convertToDto(basket);
    }
}
