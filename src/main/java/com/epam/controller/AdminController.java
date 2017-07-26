package com.epam.controller;

import com.epam.service.DishService;
import com.epam.service.OrderService;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

    private final UserService userService;

    private final DishService dishService;

    private final OrderService orderService;

    @Autowired
    public AdminController(UserService userService, DishService dishService, OrderService orderService) {
        this.userService = userService;
        this.dishService = dishService;
        this.orderService = orderService;
    }

    @Transactional
    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin";
    }

    @Transactional
    @RequestMapping(value = "/admin/seeUsers", method = RequestMethod.POST)
    public String users(Model model) {
        return "redirect:/users";
    }

    @Transactional
    @RequestMapping(value = "/admin/seeOrders", method = RequestMethod.POST)
    public String orders(Model model) {
        return "redirect:/orders";
    }

    @Transactional
    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String getUsers(Model model) {
        model.addAttribute("getAllUsers", userService.getAllUsers());
        return "users";
    }

    @RequestMapping(value = "/delete/{userId}")
    public String removeUser(Model model, @PathVariable("userId") int id) {
        userService.removeUser(id);
        return "redirect:/users/";
    }

    @RequestMapping(value = "/makeAdmin/{userId}")
    public String makeAdmin(Model model, @PathVariable("userId") int id) {
        userService.makeAdmin(id);
        return "redirect:/users/";
    }
}
