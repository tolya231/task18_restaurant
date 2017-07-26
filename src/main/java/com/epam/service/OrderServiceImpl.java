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
    public List<Dish> getDishesList(Order order) {
        List<Dish> dishes = dishDAO.findAll();
        dishes.removeIf(dish -> dish.getOrder() == null);
        dishes.removeIf(dish -> !(dish.getOrder().getId() == order.getId()));
        return dishes;
    }

    @Override
    @Transactional
    public void addDishToOrder(int dishID, Order order) {
        Dish dishToAdd = dishDAO.getOne(dishID);
        dishToAdd.setOrder(order);
        List<Dish> dishes = order.getDishList();
        dishes.add(dishToAdd);
        orderDAO.saveAndFlush(order);
        dishDAO.saveAndFlush(dishToAdd);
    }

    @Override
    public int getPrice(User user) {
        return cost(user.getOrder());
    }


    private int cost(Order order) {
        if (order.getDishList() == null)
            return 0;
        else {
            int price = 0;
            for (Dish dish : order.getDishList()) {
                price += dish.getPrice();
            }
            return price;
        }
    }

    @Override
    public String getStatus(Order order) {
        return order.getStatus();
    }

    @Override
    public void payOrder(String username) {
        User user = userDAO.findByUsername(username);
        Order order = user.getOrder();
        user.setMoney(user.getMoney() - cost(order));
        order.setStatus("NOT_ORDERED");
        order.setDishList(null);
        List<Dish> dishes = dishDAO.findAll();
        for (Dish dish : dishes) {
            if (dish.getOrder() != null && dish.getOrder().getId() == order.getId()) {
                dish.setOrder(null);
                dishDAO.saveAndFlush(dish);
            }
        }
        userDAO.saveAndFlush(user);
        orderDAO.saveAndFlush(order);
    }

    @Override
    public List<Order> getAcceptedOrders() {
        List<Order> orders = orderDAO.findAll();
        orders.removeIf(order -> !(order.getStatus().equals("IN_PROGRESS")));
        return orders;
    }

    @Override
    @Transactional
    public void addOrderToUser(User user) {
        Order order = new Order();
        order.setUser(user);
        order.setStatus("NOT_ORDERED");
        user.setOrder(order);
        orderDAO.saveAndFlush(order);
    }

    @Override
    public void acceptOrder(int orderId) {
        Order order = orderDAO.getOne(orderId);
        order.setStatus("ACCEPTED");
        orderDAO.saveAndFlush(order);
    }
}
