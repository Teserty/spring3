package com.teserty.spring3.enities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Comment {
    @Id
    private long id;
    private String text;
    @ManyToOne
    private User author;
}
