package com.teserty.spring3.controllers;

import com.teserty.spring3.conventor.Converter;
import com.teserty.spring3.dto.Response;
import com.teserty.spring3.dto.ShopDto;
import com.teserty.spring3.entity.Comment;
import com.teserty.spring3.entity.FeedBack;
import com.teserty.spring3.entity.Shop;
import com.teserty.spring3.entity.User;
import com.teserty.spring3.exceptions.ShopNotFoundException;
import com.teserty.spring3.exceptions.UserNotFoundException;
import com.teserty.spring3.services.CommentServiceImp;
import com.teserty.spring3.services.FeedbackServiceImp;
import com.teserty.spring3.services.ShopsServiceImp;
import com.teserty.spring3.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shops")
public class ShopsController {
    private final ShopsServiceImp shopsService;
    private final UserServiceImp userService;
    private final CommentServiceImp commentService;
    private final FeedbackServiceImp feedbackService;
    private final Converter converter;
    @Autowired
    public ShopsController(UserServiceImp userService,
                           FeedbackServiceImp feedbackService,
                           CommentServiceImp commentService,
                           ShopsServiceImp shopsService, Converter converter) {
        this.userService = userService;
        this.feedbackService = feedbackService;
        this.commentService = commentService;
        this.shopsService = shopsService;
        this.converter = converter;
    }
    @GetMapping("/shop/{id}")
    public ShopDto getShopById(@PathVariable Long id){
        return shopsService.findById(id);
    }
    @GetMapping("/{page}")
    public List<ShopDto> getShopsPages(@PathVariable int page){
        Pageable pageable = PageRequest.of(page, 20);
        return shopsService.findAll(pageable);
    }
    @GetMapping("/shop/{name}")
    public ShopDto getShopByName(@PathVariable String name){
        return shopsService.findByName(name);
    }
    @PostMapping("/comment-item/{id}")
    public Response writeCommentToShop(@PathVariable String id, @RequestParam String text,@RequestParam String token){
        Optional<User> user = userService.getCurrentUser(token);
        Optional<Shop> shop = shopsService.getById(Long.valueOf(id));
        if (user.isPresent()) {
            Comment comment = Comment.builder()
                    .author(user.get())
                    .text(text)
                    .creationTime(Time.valueOf(LocalTime.now()))
                    .build();
            if (shop.isPresent()){
                commentService.save(comment);
                shop.get().getComments().add(comment);
                shopsService.save(shop.get());
            }else {
                throw new ShopNotFoundException();
            }
            return new Response("200", "Test");
        }else{
            throw new UserNotFoundException();
        }
    }

    @PostMapping("/feedback-item/{id}")
    public Response postFeedbackToItem(@PathVariable String id, @RequestParam Integer rating,@RequestParam String token){
        Optional<User> user = userService.getCurrentUser(token);
        Optional<Shop> shop = shopsService.getById(Long.valueOf(id));
        if (user.isPresent()) {
            FeedBack feedBack = FeedBack.builder()
                    .author(user.get())
                    .rating(rating)
                    .build();
            if (shop.isPresent()){
                feedbackService.save(feedBack);
                shop.get().getFeedBackList().add(feedBack);
                shopsService.save(shop.get());
            }else {
                throw new ShopNotFoundException();
            }
            return new Response("200", "Test");
        }else{
            throw new UserNotFoundException();
        }
    }
    @PostMapping("/new-shop")
    public Response createNewShop(ShopDto shopDto){
        shopsService.createNewShop(converter.convertFromDTO(shopDto));
        return new Response("200", "OK");
    }
}
