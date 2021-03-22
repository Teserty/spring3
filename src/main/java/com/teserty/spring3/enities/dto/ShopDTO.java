package com.teserty.spring3.enities.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
public class ShopDTO implements Serializable {
    private String name;
    private List<FeedbackDTO> feedBackList;
    private List<CommentDTO> comments;
    private String description;
}
