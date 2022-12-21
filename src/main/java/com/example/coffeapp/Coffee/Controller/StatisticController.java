package com.example.coffeapp.Coffee.Controller;

import com.example.coffeapp.Coffee.Service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StatisticController {

    private final LocationService locationService;

    @GetMapping("/admin-statistics")
    public String findAll() {

        return "/Admin/admin-statistics";
    }
}
