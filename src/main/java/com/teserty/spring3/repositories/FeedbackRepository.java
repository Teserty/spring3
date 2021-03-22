package com.teserty.spring3.repositories;

import com.teserty.spring3.enities.FeedBack;
import com.teserty.spring3.enities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<FeedBack, String> {
    FeedBack findByAuthor(User author);
}