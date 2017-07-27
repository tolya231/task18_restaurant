package com.epam.service;

import com.epam.model.Dish;

import java.util.List;

public interface DishService {

    List<Dish> getAllDishes();

    void removeDish(int id, String username);

    Dish findById(int id);

    String getNameById(int dishId);
}
