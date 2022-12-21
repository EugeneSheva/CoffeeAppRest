package com.example.coffeapp.Coffee.Repository;


import com.example.coffeapp.Coffee.Model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
