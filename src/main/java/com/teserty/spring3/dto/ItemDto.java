package com.teserty.spring3.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder
public class ItemDto{
    private String name;
    private ShopDto shop;
    String description;
    String price;
    private List<CommentDto> comments;
    private List<FeedbackDto> feedBackList;
}
