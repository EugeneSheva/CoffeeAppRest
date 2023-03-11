package com.example.coffeapp.Coffee.Model.Order;

import lombok.Data;

import java.util.List;


@Data
public class OrderedProductDTO {

        Long id;

        Long productId;

        List<Long> coffeeAdditiveList;

        Long quantity;

        String size;








}
