package com.teserty.spring3.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class BasketDto {
    List<ItemDto> itemDtos;
}
