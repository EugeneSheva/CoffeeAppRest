package com.example.coffeapp.Coffee.Repository;


import com.example.coffeapp.Coffee.Model.Product.Dessert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DessertsRepository extends JpaRepository<Dessert, Long> {
}
