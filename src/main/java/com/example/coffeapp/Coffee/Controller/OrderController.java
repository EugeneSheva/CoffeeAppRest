package com.example.coffeapp.Coffee.Controller;

import com.example.coffeapp.Coffee.Service.CoffeeService;
import com.example.coffeapp.Coffee.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/admin-order")
    public String findAll() {

        return "/Admin/admin-orders";
    }
}
