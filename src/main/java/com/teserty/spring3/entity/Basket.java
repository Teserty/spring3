package com.teserty.spring3.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
public class Basket extends BaseEntity{
    private User owner;
    @ManyToMany
    private List<Item> itemsList;
}
