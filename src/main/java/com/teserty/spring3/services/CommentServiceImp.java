package com.teserty.spring3.services;

import com.teserty.spring3.entity.Comment;
import com.teserty.spring3.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp {
    private CommentRepository commentRepository;
    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }
}
