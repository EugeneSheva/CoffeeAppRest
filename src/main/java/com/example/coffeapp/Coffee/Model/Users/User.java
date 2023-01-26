package com.example.coffeapp.Coffee.Model.Users;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;


@Data
@Entity
@Table(name = "users")
public class User{
//    @Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private Integer phoneNumber;
    private String password;
    private boolean isActive;
    private String name;
    private String role;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfRegistry;

    private String language;

    public List getRolesList() {
        List<GrantedAuthority> authorities = Arrays.stream(this.role.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
     return authorities;
    }



//    @OneToMany
//    @JoinColumn(name="username")
//    List<Authorities> authorities;



//    @OneToMany
//    @JoinColumn(name = "userId")
//    List<Order> orderList = new ArrayList<>();
//
//    public void addOrder(Order order) {
//        this.orderList.add(order);
//    }
}
