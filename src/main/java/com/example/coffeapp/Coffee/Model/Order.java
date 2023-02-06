package com.example.coffeapp.Coffee.Model;

import com.example.coffeapp.Coffee.Model.Product.Product;
import com.example.coffeapp.Coffee.Model.Users.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "ordr")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Double price;
    @Enumerated(EnumType.STRING)

    DeliveryType deliveryType;
    @Enumerated(EnumType.STRING)

    PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date dateOfOrder;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn (name = "userId")
    User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "locationId")
    Location location;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    Address address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId")
    List<OrderedProduct> orderedProductList = new ArrayList<>();
    public void addOrderedProductList(OrderedProduct orderedProduct) {
        this.orderedProductList.add(orderedProduct);
    }


    public String getNameOfOrderedProducts() {
        String str = "";
        for (int i = 0; i < orderedProductList.size(); i++) {
            str = str + ", " + orderedProductList.get(i).getProduct();
        }
        return str;
    }

}
