package com.example.coffeapp.Coffee.Model.Order;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class AddressDTO {
    String name;
    Long phoneNumber;
    City city;
    String street;
    String house;
    String porch;
    String apartment;
    LocalDateTime dateOfDelivery;


}
