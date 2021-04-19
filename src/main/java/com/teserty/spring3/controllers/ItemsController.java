package com.teserty.spring3.controllers;

import com.teserty.spring3.conventor.Converter;
import com.teserty.spring3.dto.ItemDto;
import com.teserty.spring3.dto.Response;
import com.teserty.spring3.entity.Comment;
import com.teserty.spring3.entity.FeedBack;
import com.teserty.spring3.entity.Item;
import com.teserty.spring3.entity.User;
import com.teserty.spring3.exceptions.ItemNotFoundException;
import com.teserty.spring3.exceptions.UserNotFoundException;
import com.teserty.spring3.services.CommentServiceImp;
import com.teserty.spring3.services.FeedbackServiceImp;
import com.teserty.spring3.services.ItemServiceImp;
import com.teserty.spring3.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

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
    public Response writeCommentToItem(@PathVariable String id, @RequestParam String text,@RequestParam String token){
        Optional<User> user = userService.getCurrentUser(token);
        Optional<Item> item = itemService.getById(Long.valueOf(id));
        if (user.isPresent()) {
            Comment comment = Comment.builder()
                    .author(user.get())
                    .text(text)
                    .creationTime(Time.valueOf(LocalTime.now()))
                    .build();
            if (item.isPresent()){
                commentService.save(comment);
                item.get().getComments().add(comment);
                itemService.save(item.get());
            }else {
                throw new ItemNotFoundException();
            }
            commentService.save(comment);
            return new Response("200", "Test");
        }else{
            throw new UserNotFoundException();
        }
    }

    @PostMapping("/feedback-item/{id}")
    public Response postFeedbackToItem(@PathVariable String id, @RequestParam Integer rating,@RequestParam String token){
        Optional<User> user = userService.getCurrentUser(token);
        Optional<Item> item = itemService.getById(Long.valueOf(id));
        if (user.isPresent()) {
            FeedBack feedBack = FeedBack.builder()
                    .author(user.get())
                    .rating(rating)
                    .build();
            if (item.isPresent()){
                feedbackService.save(feedBack);
                item.get().getFeedBackList().add(feedBack);
                itemService.save(item.get());
            }else {
                throw new ItemNotFoundException();
            }
            return new Response("200", "Test");
        }else{
            throw new UserNotFoundException();
        }
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
