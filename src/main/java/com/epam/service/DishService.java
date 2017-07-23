package com.epam.service;

import com.epam.model.Dish;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DishService {

    List<Dish> getAllDishes();

    Dish findById(int id);
}
