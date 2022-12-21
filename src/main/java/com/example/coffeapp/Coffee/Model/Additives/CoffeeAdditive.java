package com.example.coffeapp.Coffee.Model.Additives;

import com.example.coffeapp.Coffee.Model.Location;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class CoffeeAdditive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Double price;
    Type type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "locationId")
    Location location;
}
