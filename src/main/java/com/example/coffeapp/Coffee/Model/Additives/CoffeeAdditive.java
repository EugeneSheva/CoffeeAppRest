package com.example.coffeapp.Coffee.Model.Additives;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class CoffeeAdditive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Double price;
    @Enumerated (EnumType.STRING)
    CoffeeAdditiveType coffeeAdditiveType;

//    @ManyToMany(mappedBy = "coffeeAdditiveList")
//    List<OrderedProduct> orderedProductList;

}
