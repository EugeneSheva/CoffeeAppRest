package com.example.coffeapp.Coffee.RestController;

import com.example.coffeapp.Coffee.Model.Location;
import com.example.coffeapp.Coffee.Model.Order;

import com.example.coffeapp.Coffee.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MenuOrderRestController {

    private final ProductService productService;
    private final OrderService orderService;
    private final CoffeeService coffeeService;
    private final TeaService teaService;
    private final DessertService dessertService;
    private final SandwichService sandwichService;
    private final SnackService snackService;
    private final CoffeeAdditivesService coffeeAdditivesService;
    private final LocationService locationService;

    @GetMapping(path = "/menu")
    public @ResponseBody Map<String, List> getAll() {
        Map<String, List>menu = new HashMap<>();
        if (coffeeService.findAll() != null) menu.put("Coffees", coffeeService.findAll());
        if (teaService.findAll() != null) menu.put("Teas", teaService.findAll());
        if (dessertService.findAll() != null) menu.put("Desserts", dessertService.findAll());
        if (snackService.findAll() != null) menu.put("Snacks", snackService.findAll());
        if (sandwichService.findAll() != null) menu.put("Sandwiches", sandwichService.findAll());
        if (coffeeAdditivesService.findAll() != null) menu.put("CoffeeAdditives", coffeeAdditivesService.findAll());
        return menu;
    }

    @PostMapping("/order")
    public @ResponseBody Order createOrder(@RequestBody Order order) {
        System.out.println(order);
        Order newOrder = orderService.save(order);
        return newOrder;
    }

    @GetMapping(path = "/orders/{id}")
    public @ResponseBody List<Order> getOrderByUserId(@PathVariable("id") Long id) {
        System.out.println("start");

        System.out.println(orderService.findOrdersByUser_Id(id));
        List<Order>ordersList = orderService.findOrdersByUser_Id(id);
        System.out.println(ordersList);
        System.out.println("finish");
        return ordersList;
    }

    @GetMapping(path = "/locations")
    public @ResponseBody List<Location> getLocations() {
        List<Location>locations = locationService.findAll();
        return locations;
    }






}
