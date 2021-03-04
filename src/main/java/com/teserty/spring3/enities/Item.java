package com.teserty.spring3.enities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Item {
    @Id
    private long id;
    @ManyToOne
    private Shop shop;
    String description;
    float price;
    @OneToMany
    private List<Comment> comments;
    @OneToMany
    private List<FeedBack> feedBackList;
}
