package com.epam.service;

import com.epam.dao.DishDAO;
import com.epam.dao.OrderDAO;
import com.epam.dao.UserDAO;
import com.epam.model.Dish;
import com.epam.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishDAO dishDAO;

    private final OrderDAO orderDAO;

    private final UserDAO userDAO;

    @Autowired
    public DishServiceImpl(DishDAO dishDAO, OrderDAO orderDAO, UserDAO userDAO) {
        this.dishDAO = dishDAO;
        this.orderDAO = orderDAO;
        this.userDAO = userDAO;
    }

    @Override
    public List<Dish> getAllDishes() {
        return dishDAO.findAll();
    }

    @Override
    public void removeDish(int id, String username) {
        Order order = userDAO.findByUsername(username).getOrder();
        Dish dish = dishDAO.getOne(id);
        List<Dish> dishes = order.getDishList();

        dishes.remove(dish);
        order.setDishList(dishes);
        dish.setOrder(null);

        dishDAO.saveAndFlush(dish);
        orderDAO.saveAndFlush(order);
    }


    @Override
    public Dish findById(int id) {
        return dishDAO.findOne(id);
    }

    @Override
    public String getNameById(int dishId) {
        return dishDAO.getOne(dishId).getName();
    }
}
