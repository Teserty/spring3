package com.teserty.spring3.repositories;

import com.teserty.spring3.enities.Comment;
import com.teserty.spring3.enities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByAuthor(User author);
}