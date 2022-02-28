package com.group4.entities;

import com.group4.entities.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;

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
public class Cart extends BaseEntity<Integer> {

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer costumer;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(name = "phone_number")
    private Long phoneNumber;
    private boolean done;

    @ManyToMany(cascade=CascadeType.REMOVE)
    @JoinTable(name = "cart_product",
            joinColumns = {@JoinColumn(name = "cart_id", unique = false)},
            inverseJoinColumns = {@JoinColumn(name = "product_id", unique = false)}
    )
    @CollectionId(
            column = @Column(name = "id", columnDefinition = "SERIAL"),
            type = @Type(type = "int"),
            generator = "native"
    )
    private List<Product> products = new ArrayList<>();

}
