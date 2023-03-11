package com.example.coffeapp.Coffee.RestController;

import com.example.coffeapp.Coffee.Model.Location;
import com.example.coffeapp.Coffee.Model.Order.AddressDTO;
import com.example.coffeapp.Coffee.Model.Order.Order;
import com.example.coffeapp.Coffee.Model.Order.OrderDTO;
import com.example.coffeapp.Coffee.Model.Product.Coffee;
import com.example.coffeapp.Coffee.Model.Users.UserDTO;
import com.example.coffeapp.Coffee.Repository.*;
import com.example.coffeapp.Coffee.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class MenuOrderRestControllerTest {
    public class JwtTokenStub {
        public String getJwtToken() {
            return "example_jwt_token";
        }
    }
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private OrderRepository orderRepository;
    @MockBean
    private CoffeeRepository coffeeRepository;
    @MockBean
    private TeaRepository teaRepository;
    @MockBean
    private DessertsRepository dessertsRepository;
    @MockBean
    private SandwichesRepository sandwichesRepository;
    @MockBean
    private SnacksRepository snacksRepository;
    @MockBean
    private CoffeeAdditivesRepository coffeeAdditivesRepository;
    @MockBean
    private LocationRepository locationRepository;
//    @InjectMocks
//    private LocationService locationService;
    @MockBean
    private UserService userService;

    private static String jwtToken;

//Login
    @Test
    public void aTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"director\",\"password\":\"director\"}"))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        jwtToken = response.substring(8, response.length() - 2);

        System.out.println("Bearer " + jwtToken);
        System.out.println("response " + response);
    }

//Get menu
    @Test
    public void bTest() throws Exception {

        List<Coffee>coffeeList = new ArrayList<>();
        Coffee coffee = new Coffee();
        coffee.setName("testCoffee");
        coffeeList.add(coffee);
        given(coffeeRepository.findAll()).willReturn(coffeeList);

        System.out.println("Bearer2 " + jwtToken);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/menu")
                .header("Authorization", "Bearer " + jwtToken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Coffees").isNotEmpty());
    }

//GetLocations
    @Test
    public void сTest() throws Exception {

        List<Location> locationList = new ArrayList<>();
        locationList.add(new Location(1L, "Test"));
        given(locationRepository.findAll()).willReturn(locationList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/locations")
                        .header("Authorization", "Bearer " + jwtToken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].address").value("Test"));
    }

//GetOrdersByUserId
    @Test
    public void dTest() throws Exception {

        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setId(1L);
        orders.add(order);
        given(orderRepository.findOrdersByUser_Id(1L)).willReturn(orders);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/1")
                        .header("Authorization", "Bearer " + jwtToken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1}]"));
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));



    }

//addUser
    @Test
    public void eTest() throws Exception {

        UserDTO userDTO = new UserDTO();
        String userDTOJson = new ObjectMapper().writeValueAsString(userDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDTOJson))
                .andDo(print())
                .andExpect(status().isOk());

    }

//addOrder
    @Test
    public void fTest() throws Exception {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setLocationId(2L);
        orderDTO.setAddress(new AddressDTO());
        String orderDTOJson = new ObjectMapper().writeValueAsString(orderDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
                        .contentType(MediaType.APPLICATION_JSON)
                 .content(orderDTOJson)
                .header("Authorization", "Bearer " + jwtToken))
                .andDo(print())
                .andExpect(status().isOk());


    }


}
