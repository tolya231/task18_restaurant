package com.epam.service;

import com.epam.dao.DishDAO;
import com.epam.dao.OrderDAO;
import com.epam.model.Dish;
import com.epam.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final DishDAO dishDAO;

    private final OrderDAO orderDAO;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO, DishDAO dishDAO) {
        this.orderDAO = orderDAO;
        this.dishDAO = dishDAO;
    }

    @Override
    public List<Dish> getDishesList(Order order) {
        if (order == null) return new ArrayList<>();
        else
            return order.getDishList();
    }

    @Override
    public void addDishToOrder(int dishID, Order order) {
        //NPE !!!!!!!!!!!!!!!
        List<Dish> list = order.getDishList();
        if (list == null)
            list = new ArrayList<>();
        list.add(dishDAO.getOne(dishID));
        order.setDishList(list);
    }
}
