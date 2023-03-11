package com.example.coffeapp.Coffee.Model;

import com.example.coffeapp.Coffee.Model.Order.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String address;
    Integer phoneNunber;
    Integer coordinates;

    public Location(Long id, String address) {
        this.id = id;
        this.address = address;
    }

    String description;
    String image;
//    @OneToMany(mappedBy = "location", fetch = FetchType.EAGER)
//    List<Order> orderList;
//
//
//    @Override
//    public String toString() {
//        return "Location{" +
//                "id=" + id +
//                ", address='" + address + '\'' +
//                ", phoneNunber=" + phoneNunber +
//                ", coordinates=" + coordinates +
//                ", description='" + description + '\'' +
//                ", image='" + image + '\'' +
//                ", orderList=" + orderList.size() +
//                '}';
//    }
}
