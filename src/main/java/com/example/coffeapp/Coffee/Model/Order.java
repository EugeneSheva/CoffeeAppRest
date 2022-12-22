package com.example.coffeapp.Coffee.Model;

import lombok.Data;


import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "ordr")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String address;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "locationId")
    Location location;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "orderId")
//    List<Products> productList;
}
