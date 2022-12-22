package com.example.coffeapp.Coffee.Model.Product;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Snack{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    String image;
    String sValue;
    Double sPrice;
}
