package com.example.coffeapp.Coffee.Service;

import com.example.coffeapp.Coffee.Model.Product.Coffee;
import com.example.coffeapp.Coffee.Repository.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;


    public Coffee findById (Long id) { return coffeeRepository.getReferenceById(id); }

    public List<Coffee> findAll() { return coffeeRepository.findAll(); }

    public Coffee save(Coffee coffee) { return coffeeRepository.save(coffee); }

    public void deleteById(Long id) { coffeeRepository.deleteById(id); }
}
