package com.example.coffeapp.Coffee.Repository;


import com.example.coffeapp.Coffee.Model.Order.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Long> {
}
