package com.teserty.spring3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
