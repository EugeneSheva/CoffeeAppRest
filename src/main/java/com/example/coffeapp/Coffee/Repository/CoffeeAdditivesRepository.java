package com.example.coffeapp.Coffee.Repository;


import com.example.coffeapp.Coffee.Model.Additives.CoffeeAdditive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeAdditivesRepository extends JpaRepository<CoffeeAdditive, Long> {
}
