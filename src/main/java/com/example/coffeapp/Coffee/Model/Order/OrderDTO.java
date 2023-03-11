package com.example.coffeapp.Coffee.Model.Order;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDTO {

    Long id;

    DeliveryType deliveryType;

    PaymentType paymentType;

    Long locationId;

    AddressDTO address;

    List<OrderedProductDTO> orderedProductList = new ArrayList<>();






}
