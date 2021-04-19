package com.teserty.spring3.controllers;

import com.teserty.spring3.conventor.Converter;
import com.teserty.spring3.dto.Response;
import com.teserty.spring3.dto.ShopDto;
import com.teserty.spring3.entity.User;
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
import java.util.List;

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
    @PostMapping("/comment-shop/{id}")
    public void writeCommentToShop(@PathVariable String id, Principal principal){

    }
    @PostMapping("/feedback-shop/{id}")
    public String postFeedbackToShop(@PathVariable String id, @RequestParam(required = true, defaultValue = "" ) Integer feedback){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getCurrentUser();
        String result = user.getUsername();
        System.out.print(result);
        return result;
    }
    @PostMapping("/new-shop")
    public Response createNewShop(ShopDto shopDto){
        shopsService.createNewShop(converter.convertFromDTO(shopDto));
        return new Response("200", "OK");
    }
}
