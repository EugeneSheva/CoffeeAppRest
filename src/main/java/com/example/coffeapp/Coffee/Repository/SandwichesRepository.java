package com.example.coffeapp.Coffee.Repository;


import com.example.coffeapp.Coffee.Model.Product.Sandwich;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SandwichesRepository extends JpaRepository<Sandwich, Long> {
}
