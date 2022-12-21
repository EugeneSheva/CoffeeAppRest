package com.example.coffeapp.Coffee.Model.Product;


import com.example.coffeapp.Coffee.Model.Additives.CoffeeAdditive;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
public class Coffee implements Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Column(length = 10000)
    String description;
    String image;
    String sValue;
    String mValue;
    String lValue;
    String xlValue;
    Double sPrice;
    Double mPrice;
    Double lPrice;
    Double xlPrice;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "coffeeId")
    List<CoffeeAdditive> coffeeAdditiveList;
}
