package com.example.coffeapp.Coffee.Repository;

import com.example.coffeapp.Coffee.Model.Pages;
import com.example.coffeapp.Coffee.Model.Users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagesRepository extends JpaRepository<Pages, Long> {
}
