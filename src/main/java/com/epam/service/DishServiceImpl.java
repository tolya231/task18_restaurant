package com.epam.service;

import com.epam.dao.DishDAO;
import com.epam.model.Dish;
import com.epam.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishDAO dishDAO;

    @Autowired
    public DishServiceImpl(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    @Override
    public List<Dish> getAllDishes() {
        return dishDAO.findAll();
    }

    @Override
    public void removeDish(int id, Order order) {
        List<Dish> dishes = order.getDishList();
        Dish dish = dishDAO.getOne(id);
        dishes.remove(dish);
        order.setDishList(dishes);
        dish.setOrder(null);
    }


    @Override
    public Dish findById(int id) {
        return dishDAO.findOne(id);
    }
}
