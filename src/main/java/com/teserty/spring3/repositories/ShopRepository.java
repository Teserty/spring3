package com.teserty.spring3.repositories;

import com.teserty.spring3.enities.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
@Component
public interface ShopRepository extends CrudRepository<Shop, Long> {
    Shop findShopById(Long id);
    List<Shop> findAll(Pageable pageable);
    Shop findByName(String name);
}
