package com.example.coffeapp.Coffee.Service;

import com.example.coffeapp.Coffee.Model.Additives.CoffeeAdditive;
import com.example.coffeapp.Coffee.Repository.CoffeeAdditivesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoffeeAdditivesService {

    private final CoffeeAdditivesRepository coffeeAdditivesRepository;


    public Optional<CoffeeAdditive> findById (Long id) { return coffeeAdditivesRepository.findById(id); }

    public List<CoffeeAdditive> findAll() { return coffeeAdditivesRepository.findAll(); }

    public CoffeeAdditive save(CoffeeAdditive coffeeAdditive) { return coffeeAdditivesRepository.save(coffeeAdditive); }

    public void deleteById(Long id) { coffeeAdditivesRepository.deleteById(id); }

    public List<CoffeeAdditive> findAllById(Iterable<Long> ids){ return coffeeAdditivesRepository.findAllById(ids); }
}
