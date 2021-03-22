package com.teserty.spring3.enities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
public class Basket {
    @Id
    private long id;
    private User owner;
    @ManyToMany
    private List<Item> itemsList;
}
