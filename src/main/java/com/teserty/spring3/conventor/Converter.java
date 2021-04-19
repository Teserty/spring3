package com.teserty.spring3.conventor;

import com.teserty.spring3.dto.ItemDto;
import com.teserty.spring3.dto.UserDto;
import com.teserty.spring3.entity.*;
import com.teserty.spring3.dto.*;
import com.teserty.spring3.services.CommentServiceImp;
import com.teserty.spring3.services.FeedbackServiceImp;
import com.teserty.spring3.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {
    @Autowired
    private UserServiceImp userService;
    @Autowired
    private CommentServiceImp commentService;
    @Autowired
    private FeedbackServiceImp feedbackService;
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
                .shop(convertToDto(item.getShop()))
                .name(item.getName())
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
    public List<FeedBack> convertDtoFeedBacks(List<FeedbackDto> feedBackList) {
        return feedBackList.stream().map(this::convertFromDTO).collect(Collectors.toList());
    }
    public FeedBack convertFromDTO(FeedbackDto feedbackDto){
        return FeedBack.builder()
                .rating(feedbackDto.getRating())
                .author(userService.getUserByUsername(feedbackDto.getAuthor()).orElse(null))
                .build();
    }
    public Comment convertFromDTO(CommentDto commentDto){
        return Comment.builder()
                .text(commentDto.getText())
                .creationTime(commentDto.getTime())
                .author(userService.getUserByUsername(commentDto.getAuthor()).orElse(null))
                .build();
    }
    public List<CommentDto> convertCommentsToDto(List<Comment> commentList) {
        return commentList.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public List<Comment> convertFromDtoToComments(List<CommentDto> commentList) {
        return commentList.stream().map(this::convertFromDTO).collect(Collectors.toList());
    }
    public Item convertFromDTO(ItemDto itemDto){
        return Item.builder()
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .price(BigDecimal.valueOf(Float.parseFloat(itemDto.getPrice())))
                .comments(convertFromDtoToComments(itemDto.getComments()))
                .build();
    }

    public List<ItemDto> convertToDto(List<Item> items) {
        return items.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Shop convertFromDTO(ShopDto shopDto) {
        return Shop.builder()
                .description(shopDto.getDescription())
                .name(shopDto.getName())
                .build();
    }
}
