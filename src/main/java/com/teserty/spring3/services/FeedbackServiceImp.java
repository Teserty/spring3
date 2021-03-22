package com.teserty.spring3.services;

import com.teserty.spring3.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImp {
    private FeedbackRepository feedbackRepository;
    @Autowired
    public void setCommentRepository(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }
}
