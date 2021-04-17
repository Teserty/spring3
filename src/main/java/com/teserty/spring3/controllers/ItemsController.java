package com.teserty.spring3.controllers;

import com.teserty.spring3.dto.ItemDto;
import com.teserty.spring3.entity.Item;
import com.teserty.spring3.services.CommentServiceImp;
import com.teserty.spring3.services.FeedbackServiceImp;
import com.teserty.spring3.services.ItemServiceImp;
import com.teserty.spring3.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/items")
public class ItemsController {
    private final UserServiceImp userService;
    private final CommentServiceImp commentService;
    private final FeedbackServiceImp feedbackService;
    private final ItemServiceImp itemService;
    @Autowired
    public ItemsController(UserServiceImp userService, FeedbackServiceImp feedbackService, CommentServiceImp commentService, ItemServiceImp itemService) {
        this.userService = userService;
        this.feedbackService = feedbackService;
        this.commentService = commentService;
        this.itemService = itemService;
    }

    @PostMapping("/comment-item/{id}")
    public void writeCommentToItem(@PathVariable String id, Principal principal){

    }

    @PostMapping("/feedback-item/{id}")
    public void postFeedbackToItem(@PathVariable String id, Principal principal){

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
