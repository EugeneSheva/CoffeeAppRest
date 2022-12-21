package com.example.coffeapp.Coffee.Service;

import com.example.coffeapp.Coffee.Model.Product.Tea;
import com.example.coffeapp.Coffee.Repository.TeaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeaService {

    private final TeaRepository teaRepository;


    public Tea findById (Long id) { return teaRepository.getReferenceById(id); }

    public List<Tea> findAll() { return teaRepository.findAll(); }

    public Tea save(Tea tea) { return teaRepository.save(tea); }

    public void deleteById(Long id) { teaRepository.deleteById(id); }

}
