package com.teserty.spring3.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Category extends BaseEntity{
    @ManyToOne
    private Category grandCategory;
}
