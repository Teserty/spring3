package com.teserty.spring3.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Time;

@Entity
@Data
@Builder
@Getter
@Setter
public class Comment extends BaseEntity{
    private String text;
    @ManyToOne
    private User author;
    private Time creationTime;
}
