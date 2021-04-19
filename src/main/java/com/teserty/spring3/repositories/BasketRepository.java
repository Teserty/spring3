package com.teserty.spring3.repositories;

import com.teserty.spring3.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    Basket findByOwner_Username(String username);
}