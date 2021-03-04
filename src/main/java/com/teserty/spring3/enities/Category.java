package com.teserty.spring3.enities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Category {
    @Id
    private Long id;
    @ManyToOne
    private Category grandCategory;
}
