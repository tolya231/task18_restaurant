package com.epam.controller;

import com.epam.Validator.UserValidator;
import com.epam.model.User;
import com.epam.service.OrderService;
import com.epam.service.SecurityService;
import com.epam.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private static final int WEAK_STRENGTH = 1;
    private static final int FEAR_STRENGTH = 5;
    private static final int STRONG_STRENGTH = 7;

    private final UserService userService;

    private final SecurityService securityService;

    private final UserValidator userValidator;

    private final OrderService orderService;

    Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    public LoginController(UserService userService, SecurityService securityService, UserValidator userValidator, OrderService orderService) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
        this.orderService = orderService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";

    }

    @RequestMapping(value = "/checkStrength", method = RequestMethod.GET, produces = { "text/html; charset=UTF-8" })
    public @ResponseBody
    String checkStrength(@RequestParam String password) {

        if (password.length() >= WEAK_STRENGTH & password.length() < FEAR_STRENGTH) {
            // добавить локализацию
            return "Слабый";
        } else if (password.length() >= FEAR_STRENGTH & password.length() < STRONG_STRENGTH) {
            return "Средний";
        } else if (password.length() >= STRONG_STRENGTH) {
            return "Сильный";
        }
        return "";
    }

    @Transactional
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        orderService.addOrderToUser(userForm);
        logger.info("в систему добавлен новый клиент");
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        return "login";
    }


}
