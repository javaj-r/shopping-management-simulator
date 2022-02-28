package com.group4.entities;

import com.group4.entities.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author javid
 * Created on 1/30/2022
 */

@Getter
@Setter
@Entity
public class Product extends BaseEntity<Integer> {

    @Column(length = 100)
    private String name;

    private int price;
    private int stock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
