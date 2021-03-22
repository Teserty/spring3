package com.teserty.spring3.controllers;

import com.teserty.spring3.services.CommentServiceImp;
import com.teserty.spring3.services.FeedbackServiceImp;
import com.teserty.spring3.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/items")
public class ItemsController {
    private final UserServiceImp userService;
    private final CommentServiceImp commentService;
    private final FeedbackServiceImp feedbackService;
    @Autowired
    public ItemsController(UserServiceImp userService, FeedbackServiceImp feedbackService, CommentServiceImp commentService) {
        this.userService = userService;
        this.feedbackService = feedbackService;
        this.commentService = commentService;
    }

    @PostMapping("/comment-item/{id}")
    public void writeCommentToItem(@PathVariable String id, Principal principal){

    }

    @PostMapping("/feedback-item/{id}")
    public void postFeedbackToItem(@PathVariable String id, Principal principal){

    }
}
