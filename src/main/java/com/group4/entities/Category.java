package com.group4.entities;

import com.group4.entities.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author javid
 * Created on 1/30/2022
 */

@Getter
@Setter
@Entity
public class Category extends BaseEntity<Integer> {

    @Column(length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parentCategory;

    @OneToMany
    @JoinColumn(name = "category_id")
    private List<Product> products = new ArrayList<>();

}
