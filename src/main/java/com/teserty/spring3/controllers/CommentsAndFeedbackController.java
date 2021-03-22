package com.teserty.spring3.controllers;

import com.teserty.spring3.enities.FeedBack;
import com.teserty.spring3.enities.User;
import com.teserty.spring3.services.CommentService;
import com.teserty.spring3.services.DTOService;
import com.teserty.spring3.services.FeedbackService;
import com.teserty.spring3.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class CommentsAndFeedbackController {
    private final UserService userService;
    private final CommentService commentService;
    private final FeedbackService feedbackService;
    @Autowired
    public CommentsAndFeedbackController(UserService userService, FeedbackService feedbackService, CommentService commentService) {
        this.userService = userService;
        this.feedbackService = feedbackService;
        this.commentService = commentService;
    }

    @PostMapping("/comment-item/{id}")
    public void writeCommentToItem(@PathVariable String id, Principal principal){

    }
    @PostMapping("/comment-shop/{id}")
    public void writeCommentToShop(@PathVariable String id, Principal principal){

    }
    @PostMapping("/feedback-item/{id}")
    public void postFeedbackToItem(@PathVariable String id, Principal principal){

    }
    @PostMapping("/feedback-shop/{id}")
    public String postFeedbackToShop(@PathVariable String id, @RequestParam(required = true, defaultValue = "" ) Integer feedback){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        /*FeedBack.builder()
                .author(userService.getCurrentUser())
                .rating(feedback)
                .build();*/
        User user = userService.getCurrentUser();
        String result = user.getUsername();
        System.out.print(result);
        return result;
    }
}
