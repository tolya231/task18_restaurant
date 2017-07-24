package com.epam.service;

import com.epam.model.Dish;

import java.util.List;

public interface DishService {

    List<Dish> getAllDishes();

    Dish findById(int id);
}
