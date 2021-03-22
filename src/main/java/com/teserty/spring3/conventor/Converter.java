package com.teserty.spring3.conventor;

import com.teserty.spring3.dto.ItemDto;
import com.teserty.spring3.dto.UserDto;
import com.teserty.spring3.enities.*;
import com.teserty.spring3.dto.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {
    public UserDto convertToDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .build();
    }

    public ItemDto convertToDto(Item item) {
        return ItemDto.builder()
                .comments(convertCommentsToDto(item.getComments()))
                .description(item.getDescription())
                .price(item.getPrice().toString())
                .feedBackList(convertFeedBacksToDto(item.getFeedBackList()))
                .build();
    }

    public ShopDto convertToDto(Shop shop) {
        return ShopDto.builder()
                .description(shop.getDescription())
                .name(shop.getName())
                .comments(convertCommentsToDto(shop.getComments()))
                .feedBackList(convertFeedBacksToDto(shop.getFeedBackList()))
                .build();
    }

    public FeedbackDto convertToDto(FeedBack feedBack) {
        return FeedbackDto.builder()
                .author(feedBack.getAuthor().getUsername())
                .rating(feedBack.getRating())
                .build();
    }

    public CommentDto convertToDto(Comment comment){
        return CommentDto.builder()
                .author(comment.getAuthor().getUsername())
                .text(comment.getText())
                .build();
    }

    public List<FeedbackDto> convertFeedBacksToDto(List<FeedBack> feedBackList) {
        return feedBackList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<CommentDto> convertCommentsToDto(List<Comment> commentList) {
        return commentList.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
