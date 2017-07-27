package com.epam.controller;

import com.epam.service.DishService;
import com.epam.service.OrderService;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.apache.log4j.Logger;

@Controller
public class AdminController {

    private final UserService userService;

    private final DishService dishService;

    private final OrderService orderService;

    Logger logger = Logger.getLogger(AdminController.class);

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

    @Transactional
    @RequestMapping(value = "orders", method = RequestMethod.GET)
    public String getOrders(Model model) {
        model.addAttribute("getAcceptedOrders", orderService.getAcceptedOrders());
        return "orders";
    }

    @Transactional
    @RequestMapping(value = "/delete/{userId}")
    public String removeUser(Model model, @PathVariable("userId") int id) {
        userService.removeUser(id);
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("админ под именем " + name + " удалил пользователя" + userService.getNameById(id));
        return "redirect:/users/";
    }

    @Transactional
    @RequestMapping(value = "/makeAdmin/{userId}")
    public String makeAdmin(Model model, @PathVariable("userId") int id) {
        userService.makeAdmin(id);
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("админ под именем " + name + " сделал пользователя" + userService.getNameById(id) + " админом");
        return "redirect:/users/";
    }

    @Transactional
    @RequestMapping(value = "/accept/{orderId}")
    public String seeOrders(Model model, @PathVariable("orderId") int id) {
        orderService.acceptOrder(id);
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("админом " + name + " подтвержден заказ клиента c id=" + id);
        return "redirect:/orders/";
    }
}
