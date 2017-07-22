package com.epam.dao;

import com.epam.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishDAO extends JpaRepository<Dish, Integer> {

}
