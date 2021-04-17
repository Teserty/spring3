package com.teserty.spring3.repositories;

import com.teserty.spring3.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
