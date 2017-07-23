package com.epam.controller;

import com.epam.model.Dish;
import com.epam.model.User;
import com.epam.service.DishService;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@Controller
public class ClientController {

    private final UserService userService;

    private final DishService dishService;

    @Autowired
    public ClientController(UserService userService, DishService dishService) {
        this.userService = userService;
        this.dishService = dishService;
    }

    @Transactional
    @RequestMapping("/add/{id}")
    public String removeHotel(Model model, @PathVariable("id") int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.findByUsername(name);
        Set<Dish> dishes = user.getDishes();
        dishes.add(dishService.findById(id));
        user.setDishes(dishes);
        return "redirect:/welcome";
    }

    @Transactional
    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        model.addAttribute("getAllDishes", this.dishService.getAllDishes());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.findByUsername(name);
        model.addAttribute("orderList", user.getDishes());
        return "welcome";
    }

}
