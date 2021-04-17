package com.teserty.spring3.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Builder
public class Item extends BaseEntity{
    private String name;
    @ManyToOne
    private Shop shop;
    String description;
    BigDecimal price;
    @OneToMany
    private List<Comment> comments;
    @OneToMany
    private List<FeedBack> feedBackList;
}
