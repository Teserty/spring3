package com.teserty.spring3.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
public class ShopDto implements Serializable {
    private String name;
    private List<FeedbackDto> feedBackList;
    private List<CommentDto> comments;
    private String description;
}
