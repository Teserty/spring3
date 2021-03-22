package com.teserty.spring3.enities;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Time;

@Entity
@Data
@Builder
@Getter
@Setter
public class Comment {
    @Id
    private long id;
    private String text;
    @ManyToOne
    private User author;
    private Time creationTime;
}
