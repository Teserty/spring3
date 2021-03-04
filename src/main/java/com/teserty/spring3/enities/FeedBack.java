package com.teserty.spring3.enities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class FeedBack {
    @Id
    String id;
    Integer rating;
    @ManyToOne
    private User author;
}
