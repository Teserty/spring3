package com.teserty.spring3.repositories;

import com.teserty.spring3.entity.FeedBack;
import com.teserty.spring3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<FeedBack, String> {
    FeedBack findByAuthor(User author);
}