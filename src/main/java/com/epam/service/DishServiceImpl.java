package com.epam.service;

import com.epam.dao.DishDAO;
import com.epam.dao.OrderDAO;
import com.epam.dao.UserDAO;
import com.epam.model.Dish;
import com.epam.model.Order;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishDAO dishDAO;

    private final OrderDAO orderDAO;

    private final UserDAO userDAO;

    private Logger logger = Logger.getLogger(DishServiceImpl.class);

    @Autowired
    public DishServiceImpl(DishDAO dishDAO, OrderDAO orderDAO, UserDAO userDAO) {
        this.dishDAO = dishDAO;
        this.orderDAO = orderDAO;
        this.userDAO = userDAO;
    }

    @Override
    public List<Dish> getAllDishes() {
        try {
            return dishDAO.findAll();

        } catch (Exception ex) {
            logger.error("Ошибка при получении списка блюд");
            return null;
        }
    }

    @Override
    public void removeDish(int id, String username) {
        try {
            Order order = userDAO.findByUsername(username).getOrder();
            Dish dish = dishDAO.getOne(id);
            List<Dish> dishes = order.getDishList();

            dishes.remove(dish);
            order.setDishList(dishes);
            dish.setOrder(null);
        } catch (Exception ex) {
            logger.error("ошибка при удалении блюда из заказа");
        }
        /*dishDAO.saveAndFlush(dish);
        orderDAO.saveAndFlush(order);*/
    }


    @Override
    public Dish findById(int id) {
        try {
            return dishDAO.findOne(id);
        } catch (Exception ex) {
            logger.error("ошибка при поиске блюда по id");
            return null;
        }
    }

    @Override
    public String getNameById(int dishId) {
        try {
            return dishDAO.getOne(dishId).getName();
        } catch (Exception ex) {
            logger.error("ошибка при поиске названия блюда по id");
            return null;
        }
    }
}
