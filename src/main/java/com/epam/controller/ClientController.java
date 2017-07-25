package com.epam.controller;

import com.epam.model.Order;
import com.epam.model.User;
import com.epam.service.DishService;
import com.epam.service.OrderService;
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

@Controller
public class ClientController {

    private final UserService userService;

    private final DishService dishService;

    private final OrderService orderService;

    @Autowired
    public ClientController(UserService userService, DishService dishService, OrderService orderService) {
        this.userService = userService;
        this.dishService = dishService;
        this.orderService = orderService;
    }

    @Transactional
    @RequestMapping("/add/{id}")
    public String addDish(Model model, @PathVariable("id") int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        orderService.addDishToOrder(id, userService.getOrderByUsername(name));
        return "redirect:/welcome";
    }

    @Transactional
    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(name);
        orderService.addOrderToUser(user);
        Order order = userService.getOrderByUsername(name);
        model.addAttribute("getPrice", orderService.getPrice(order));
        model.addAttribute("isAdmin", userService.isAdmin(user));
        model.addAttribute("getAllDishes", dishService.getAllDishes());
        model.addAttribute("orderList", orderService.getDishesList(order));
        return "welcome";
    }

    @RequestMapping("/remove/{dishId}")
    @Transactional
    public String removeOrderedDish(Model model, @PathVariable("dishId") int id)  {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(name);
        Order order = user.getOrder();
        dishService.removeDish(id, order);
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/welcome/make", method = RequestMethod.POST)
    @Transactional
    public String makeOrder() {
        return "redirect:/welcome";
    }
}
