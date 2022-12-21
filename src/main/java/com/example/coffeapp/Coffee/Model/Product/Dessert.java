package com.example.coffeapp.Coffee.Model.Product;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Dessert implements Products{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    String image;
    String value;
    Double price;
}

