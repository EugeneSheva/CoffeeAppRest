package com.example.coffeapp.Coffee.Repository;


import com.example.coffeapp.Coffee.Model.Product.Snack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnacksRepository extends JpaRepository<Snack, Long> {
}
