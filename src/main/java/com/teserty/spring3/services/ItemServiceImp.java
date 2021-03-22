package com.teserty.spring3.services;

import com.teserty.spring3.enities.Item;
import com.teserty.spring3.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImp {
    private ItemRepository itemRepository;
    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void createNewItem(Item item){
        itemRepository.save(item);
    }
}
