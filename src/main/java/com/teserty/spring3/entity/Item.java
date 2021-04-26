package com.teserty.spring3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Size(min = 1)
    String description;
    @Min(0)
    BigDecimal price;
    @OneToMany
    private List<Comment> comments;
    @OneToMany
    private List<FeedBack> feedBackList;
}
