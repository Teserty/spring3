package com.teserty.spring3.enities;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@Builder
@Getter
@Setter
public class FeedBack {
    @Id
    private String id;
    private Integer rating;
    @ManyToOne
    private User author;

}
