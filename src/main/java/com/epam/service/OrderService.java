package com.epam.service;

import com.epam.model.Dish;
import com.epam.model.Order;
import com.epam.model.User;

import java.util.List;

public interface OrderService {
    List<Dish> getDishesList(Order order);

    void addDishToOrder(int dishID, Order order);

    void addOrderToUser(User user);

    int getPrice(User user);


    String getStatus(Order order);

    void payOrder(String username);

    List<Order> getAcceptedOrders();

    void acceptOrder(int orderId);
}
