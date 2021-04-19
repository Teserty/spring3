package com.teserty.spring3.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FeedBack extends BaseEntity{
    @Id
    private String id;
    private Integer rating;
    @ManyToOne
    private User author;

}
