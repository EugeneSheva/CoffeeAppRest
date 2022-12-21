package com.example.coffeapp.Coffee.Repository;


import com.example.coffeapp.Coffee.Model.Product.Tea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeaRepository extends JpaRepository<Tea, Long> {
}
