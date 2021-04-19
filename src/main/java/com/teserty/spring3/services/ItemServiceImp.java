package com.teserty.spring3.services;

import com.teserty.spring3.conventor.Converter;
import com.teserty.spring3.dto.ItemDto;
import com.teserty.spring3.entity.Item;
import com.teserty.spring3.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImp implements ItemService {
    private ItemRepository itemRepository;
    private final Converter converter;

    public ItemServiceImp(Converter converter) {
        this.converter = converter;
    }

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void createNewItem(Item item){
        itemRepository.save(item);
    }

    public void createNewItem(ItemDto itemDto) {
        itemRepository.save(converter.convertFromDTO(itemDto));
    }

    public Optional<Item> getById(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> getAll(){return itemRepository.findAll();}

    public void save(Item item) {
        itemRepository.save(item);
    }
}
