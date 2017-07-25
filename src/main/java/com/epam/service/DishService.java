package com.epam.service;

import com.epam.model.Dish;
import com.epam.model.Order;

import java.util.List;

public interface DishService {

    List<Dish> getAllDishes();

    void removeDish(int id, Order order);


    Dish findById(int id);
}
