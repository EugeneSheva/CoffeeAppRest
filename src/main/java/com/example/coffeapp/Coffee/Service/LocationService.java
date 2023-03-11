package com.example.coffeapp.Coffee.Service;

import com.example.coffeapp.Coffee.Model.Location;
import com.example.coffeapp.Coffee.Repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;


    public Optional<Location> findById (Long id) { return locationRepository.findById(id); }

    public List<Location> findAll() {
        System.out.println("Location service");
        return locationRepository.findAll(); }

    public Location save(Location location) { return locationRepository.save(location); }

    public void deleteById(Long id) { locationRepository.deleteById(id); }
}
