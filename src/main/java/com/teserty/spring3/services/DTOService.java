package com.teserty.spring3.services;

import com.sun.el.stream.Stream;
import com.teserty.spring3.enities.*;
import com.teserty.spring3.enities.dto.*;
import org.hibernate.mapping.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DTOService {
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    private ModelMapper modelMapper;

    public UserDTO convertToDto(User user) {
        UserDTO userDto = modelMapper.map(user, UserDTO.class);
        return userDto;
    }
    public ItemDTO convertToDto(Item item) {
        ItemDTO itemDto = modelMapper.map(item, ItemDTO.class);
        return itemDto;
    }
    public ShopDTO convertToDto(Shop shop) {
        ShopDTO shopDto = modelMapper.map(shop, ShopDTO.class);
        return shopDto;
    }
    public FeedbackDTO convertToDto(FeedBack feedBack) {
        FeedbackDTO feedBackDto = modelMapper.map(feedBack, FeedbackDTO.class);
        return feedBackDto;
    }
    public List<FeedbackDTO> convertFeedBacksToDto(List<FeedBack> feedBackList) {
        List<FeedbackDTO> feedBacks = new LinkedList<>();
        for(FeedBack feedBack: feedBackList)
            feedBacks.add(modelMapper.map(feedBack, FeedbackDTO.class));
        return feedBacks;
    }
    public List<CommentDTO> convertCommentsToDto(List<Comment> commentList) {
        List<CommentDTO> commentDTOS = new LinkedList<>();
        for(Comment comment: commentList)
            commentDTOS.add(modelMapper.map(comment, CommentDTO.class));
        return commentDTOS;
    }
}
