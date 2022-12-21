package com.example.coffeapp.Coffee.Repository;


import com.example.coffeapp.Coffee.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
