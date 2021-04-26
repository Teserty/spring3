package com.teserty.spring3.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
    @Min(0)
    @Max(5)
    private Integer rating;
    @ManyToOne
    private User author;

}
