package com.teserty.spring3.services;

import com.teserty.spring3.dto.ItemDto;
import com.teserty.spring3.entity.Item;

public interface ItemService {
    public void createNewItem(Item item);
    public void createNewItem(ItemDto itemDto);
}
