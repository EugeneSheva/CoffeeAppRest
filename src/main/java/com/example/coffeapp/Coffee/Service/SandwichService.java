package com.example.coffeapp.Coffee.Service;

import com.example.coffeapp.Coffee.Model.Product.Sandwich;
import com.example.coffeapp.Coffee.Repository.SandwichesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SandwichService {

    private final SandwichesRepository sandwichesRepository;


    public Sandwich findById (Long id) { return sandwichesRepository.getReferenceById(id); }

    public List<Sandwich> findAll() { return sandwichesRepository.findAll(); }

    public Sandwich save(Sandwich sandwich) { return sandwichesRepository.save(sandwich); }

    public void deleteById(Long id) { sandwichesRepository.deleteById(id); }
}
