package com.example.coffeapp.Coffee.Service;

import com.example.coffeapp.Coffee.Model.Location;
import com.example.coffeapp.Coffee.Repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;


    public Location findById (Long id) { return locationRepository.getReferenceById(id); }

    public List<Location> findAll() { return locationRepository.findAll(); }

    public Location save(Location location) { return locationRepository.save(location); }

    public void deleteById(Long id) { locationRepository.deleteById(id); }
}
