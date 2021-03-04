package com.teserty.spring3.enities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Shop {
    @Id
    private long id;
    private String name;
    @OneToMany
    private List<FeedBack> feedBackList;
    @OneToMany
    private List<Comment> comments;
    private String description;
}
