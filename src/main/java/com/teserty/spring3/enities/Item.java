package com.teserty.spring3.enities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Builder
public class Item {
    @Id
    private long id;
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
