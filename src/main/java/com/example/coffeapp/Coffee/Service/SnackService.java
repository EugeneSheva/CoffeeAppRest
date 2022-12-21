package com.example.coffeapp.Coffee.Service;

import com.example.coffeapp.Coffee.Model.Product.Snack;
import com.example.coffeapp.Coffee.Repository.SnacksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SnackService {

    private final SnacksRepository snacksRepository;


    public Snack findById (Long id) { return snacksRepository.getReferenceById(id); }

    public List<Snack> findAll() { return snacksRepository.findAll(); }

    public Snack save(Snack snack) { return snacksRepository.save(snack); }

    public void deleteById(Long id) { snacksRepository.deleteById(id); }
}
