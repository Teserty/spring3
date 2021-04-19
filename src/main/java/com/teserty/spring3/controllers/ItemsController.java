package com.teserty.spring3.controllers;

import com.teserty.spring3.conventor.Converter;
import com.teserty.spring3.dto.ItemDto;
import com.teserty.spring3.dto.Response;
import com.teserty.spring3.entity.Item;
import com.teserty.spring3.services.CommentServiceImp;
import com.teserty.spring3.services.FeedbackServiceImp;
import com.teserty.spring3.services.ItemServiceImp;
import com.teserty.spring3.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsController {
    private final UserServiceImp userService;
    private final CommentServiceImp commentService;
    private final FeedbackServiceImp feedbackService;
    private final ItemServiceImp itemService;
    private final Converter converter;
    @Autowired
    public ItemsController(UserServiceImp userService, FeedbackServiceImp feedbackService, CommentServiceImp commentService, ItemServiceImp itemService, Converter converter) {
        this.userService = userService;
        this.feedbackService = feedbackService;
        this.commentService = commentService;
        this.itemService = itemService;
        this.converter = converter;
    }

    @PostMapping("/comment-item/{id}")
    public Response writeCommentToItem(@PathVariable String id, Authentication authentication){
        userService.getCurrentUser();
        return new Response("200", "Test");
    }

    @PostMapping("/feedback-item/{id}")
    public void postFeedbackToItem(@PathVariable String id, Authentication authentication){

    }
    @GetMapping("/")
    public List<ItemDto> getItems(){
        return converter.convertToDto(itemService.getAll());
    }
    @PostMapping("/new/item")
    public void createItem(@RequestBody ItemDto itemDto, Principal principal){
        itemService.createNewItem(itemDto);
    }
    @GetMapping("/{id}")
    public Item getById(@PathVariable Long id){
        return itemService.getById(id).orElse(Item.builder().build());
    }
}
