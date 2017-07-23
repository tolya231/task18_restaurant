package com.epam.service;

import com.epam.dao.DishDAO;
import com.epam.model.Dish;
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
    public Dish findById(int id) {
        return dishDAO.findOne(id);
    }
}
