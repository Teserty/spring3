package com.teserty.spring3.enities.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
public class ItemDTO implements Serializable {
    private ShopDTO shop;
    String description;
    float price;
    private List<CommentDTO> comments;
    private List<FeedbackDTO> feedBackList;
}
