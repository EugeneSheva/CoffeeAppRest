package com.example.coffeapp.Coffee.RestController;

import com.example.coffeapp.Coffee.Model.Additives.CoffeeAdditive;
import com.example.coffeapp.Coffee.Model.Location;
import com.example.coffeapp.Coffee.Model.Order.*;

import com.example.coffeapp.Coffee.Model.Product.Coffee;
import com.example.coffeapp.Coffee.Model.Product.Product;
import com.example.coffeapp.Coffee.Security.JwtUtil;
import com.example.coffeapp.Coffee.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @GetMapping(path = "/menu")
    public @ResponseBody Map<String, List> getMenu() {
        Map<String, List> menu = new HashMap<>();
        if (coffeeService.findAll() != null) menu.put("Coffees", coffeeService.findAll());
        if (teaService.findAll() != null) menu.put("Teas", teaService.findAll());
        if (dessertService.findAll() != null) menu.put("Desserts", dessertService.findAll());
        if (snackService.findAll() != null) menu.put("Snacks", snackService.findAll());
        if (sandwichService.findAll() != null) menu.put("Sandwiches", sandwichService.findAll());
        if (coffeeAdditivesService.findAll() != null) menu.put("CoffeeAdditives", coffeeAdditivesService.findAll());
        return menu;
    }

    @PostMapping("/order")
    public @ResponseBody Order createOrder(@RequestBody OrderDTO orderDTO, HttpServletRequest request) {
        Order order = new Order();
        order.setDateOfOrder(new Date());
        order.setDeliveryType(orderDTO.getDeliveryType());
        order.setOrderStatus(OrderStatus.ORDERED);
        order.setPaymentType(orderDTO.getPaymentType());
        order.setLocation(locationService.findById(orderDTO.getLocationId()).orElse(new Location()));
//      order.setUser
        final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            System.out.println(jwt);
            System.out.println(jwtUtil.extractUsername(jwt));
            username = jwtUtil.extractUsername(jwt);
        }
        order.setUser(userService.findByUserName(username));
//      order.setOrderedProductList
        Double orderPriceTMP = 0d;
        List<OrderedProduct> orderedProducts = new ArrayList<>();
        for (OrderedProductDTO orderedProductDTO : orderDTO.getOrderedProductList()) {
            OrderedProduct orderedProduct = new OrderedProduct();
            Product product = productService.findById(orderedProductDTO.getProductId()).orElseThrow();
            orderedProduct.setProduct(product);
            orderedProduct.setSize(orderedProductDTO.getSize());
            orderedProduct.setQuantity(orderedProductDTO.getQuantity());
            Double orderedProductPriceTMP = 0d;


            if (productService.getType(orderedProductDTO.getProductId()).equalsIgnoreCase("Coffee")) {
                Coffee coffee = coffeeService.findById(orderedProductDTO.getProductId());
                if (orderedProductDTO.getSize().equalsIgnoreCase("S")) {
                    if (coffee.getSPrice() > 0) {
                        System.out.println("до "+orderedProductPriceTMP);
                        orderedProductPriceTMP += coffee.getSPrice();
                        System.out.println("после "+orderedProductPriceTMP);
                    } else {
                        break;
                    }
                } else if (orderedProductDTO.getSize().equalsIgnoreCase("M")) {
                    if (coffee.getMPrice() > 0) {
                        System.out.println("до "+orderedProductPriceTMP);
                        orderedProductPriceTMP += coffee.getMPrice();
                        System.out.println("после "+orderedProductPriceTMP);
                    } else {
                        break;
                    }
                } else if (orderedProductDTO.getSize().equalsIgnoreCase("L")) {
                    if (coffee.getLPrice() > 0) {
                        System.out.println("до "+orderedProductPriceTMP);
                        orderedProductPriceTMP += coffee.getLPrice();
                        System.out.println("после "+orderedProductPriceTMP);
                    } else {
                        break;
                    }
                } else if (orderedProductDTO.getSize().equalsIgnoreCase("XL")) {
                    if (coffee.getXlPrice() > 0) {
                        System.out.println("до "+orderedProductPriceTMP);
                        orderedProductPriceTMP += coffee.getXlPrice();
                        System.out.println("после "+orderedProductPriceTMP);
                    } else {
                        break;
                    }
                }
            } else {
                System.out.println("до "+orderedProductPriceTMP);
                orderedProductPriceTMP += product.getSPrice();
                System.out.println("после "+orderedProductPriceTMP);

            }
            System.out.println(product.getName()+" orProdPrice + prod " + orderedProductPriceTMP);
            List<CoffeeAdditive> coffeeAdditives = new ArrayList<>();
            for (Long aLong : orderedProductDTO.getCoffeeAdditiveList()) {
                CoffeeAdditive coffeeAdditive = coffeeAdditivesService.findById(aLong).orElseThrow();
                coffeeAdditives.add(coffeeAdditive);
                orderedProductPriceTMP += coffeeAdditive.getPrice();
                System.out.println(product.getName()+" orProdPrice + add + prod" + orderedProductPriceTMP);
            }
            orderedProduct.setCoffeeAdditiveList(coffeeAdditives);
            orderedProduct.setPrice(orderedProductPriceTMP);
            orderPriceTMP += orderedProductPriceTMP;

            orderedProducts.add(orderedProduct);
        }
        order.setOrderedProductList(orderedProducts);
        order.setPrice(orderPriceTMP);
//      order.setAddress
        Address address = new Address();
        address.setName(orderDTO.getAddress().getName());
        address.setPhoneNumber(orderDTO.getAddress().getPhoneNumber());
        address.setCity(orderDTO.getAddress().getCity());
        address.setStreet(orderDTO.getAddress().getStreet());
        address.setHouse(orderDTO.getAddress().getHouse());
        address.setPorch(orderDTO.getAddress().getPorch());
        address.setApartment(orderDTO.getAddress().getApartment());
        address.setDateOfDelivery(orderDTO.getAddress().getDateOfDelivery());
        order.setAddress(address);
        System.out.println(order);
        orderService.save(order);
        System.out.println("Complete");
        return order;
    }

    @GetMapping(path = "/orders/{id}")
    public @ResponseBody List<Order> getOrderByUserId(@PathVariable("id") Long id) {
        List<Order> ordersList = orderService.findOrdersByUser_Id(id);
        return ordersList;
    }

    @GetMapping(path = "/locations")
    public @ResponseBody List<Location> getLocations() {
        System.out.println("Location controller");
        List<Location> locations = locationService.findAll();
        return locations;
    }


}
