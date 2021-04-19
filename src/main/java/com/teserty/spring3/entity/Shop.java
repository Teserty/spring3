package com.teserty.spring3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop extends BaseEntity{
    @Id
    private long id;
    private String name;
    @OneToMany
    private List<FeedBack> feedBackList;
    @OneToMany
    private List<Comment> comments;
    private String description;
}
