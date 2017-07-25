package com.epam.service;

import com.epam.dao.DishDAO;
import com.epam.dao.OrderDAO;
import com.epam.dao.UserDAO;
import com.epam.model.Dish;
import com.epam.model.Order;
import com.epam.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final DishDAO dishDAO;

    private final OrderDAO orderDAO;

    private final UserDAO userDAO;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO, DishDAO dishDAO, UserDAO userDAO) {
        this.orderDAO = orderDAO;
        this.dishDAO = dishDAO;
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public List<Dish> getDishesList(final Order order) {
        List<Dish> dishes = dishDAO.findAll();
        dishes.removeIf(dish -> dish.getOrder() == null);
        dishes.removeIf(dish -> !(dish.getOrder().getId() == order.getId()));
        return dishes;
    }

    @Override
    public void addDishToOrder(int dishID, Order order) {
        Dish dishToAdd = dishDAO.getOne(dishID);
        dishToAdd.setOrder(order);
        List<Dish> dishes = dishDAO.findAll();
        dishes = order.getDishList();
        //dishes.removeIf(dish -> dish.getOrder() == null);
        //dishes.removeIf(dish -> !(dish.getOrder().getId() == order.getId()));
        dishes.add(dishToAdd);
        order.setDishList(dishes);
    }

    @Override
    public int getPrice(Order order) {
        List<Dish> dishes = order.getDishList();
        int price = 0;
        for (Dish dish : dishes) {
            price += dish.getPrice();
        }
        return price;
    }

    @Override
    @Transactional
    public void addOrderToUser(User user) {
        if (user.getOrder() == null) {
            Order order = new Order();
            order.setUser(user);
            user.setOrder(order);
            orderDAO.saveAndFlush(order);
        }
    }
}
