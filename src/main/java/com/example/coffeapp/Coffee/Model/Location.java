package com.example.coffeapp.Coffee.Model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;


@Data
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String address;
    Integer phoneNunber;
    Integer coordinates;
    String description;
    String image;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location", fetch = FetchType.EAGER)
    List<Order> orderList;
}
