package com.example.coffeapp.Coffee.Service;

import com.example.coffeapp.Coffee.Model.Product.Dessert;
import com.example.coffeapp.Coffee.Repository.DessertsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DessertService {

    private final DessertsRepository dessertsRepository;


    public Dessert findById (Long id) { return dessertsRepository.getReferenceById(id); }

    public List<Dessert> findAll() { return dessertsRepository.findAll(); }

    public Dessert save(Dessert dessert) { return dessertsRepository.save(dessert); }

    public void deleteById(Long id) { dessertsRepository.deleteById(id); }
}
