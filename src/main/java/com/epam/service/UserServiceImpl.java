package com.epam.service;

import com.epam.dao.OrderDAO;
import com.epam.dao.RoleDAO;
import com.epam.dao.UserDAO;
import com.epam.model.Order;
import com.epam.model.Role;
import com.epam.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    private final RoleDAO roleDAO;

    private final OrderDAO orderDAO;


    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, OrderDAO orderDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.orderDAO = orderDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDAO.getOne(2));
        user.setRoles(roles);
        user.setMoney(1000);
        userDAO.save(user);
    }

    @Override
    public void removeUser(Integer id) {
        userDAO.delete(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    @Override
    public Order getOrderByUsername(String username) {
        return userDAO.findByUsername(username).getOrder();
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public boolean isAdmin(User user) {
        Set<Role> roles = user.getRoles();
        return hasRoleAdmin(roles);
    }

    @Override
    @Transactional
    public void makeOrder(String username) {
        User user = userDAO.findByUsername(username);
        Order order = user.getOrder();
        order.setStatus("IN_PROGRESS");
        /*order.setDishList(null);
        List<Dish> dishes = dishDAO.findAll();
        for (Dish dish : dishes) {
            if (dish.getOrder() != null && dish.getOrder().getId() == order.getId()) {
                dish.setOrder(null);
                dishDAO.saveAndFlush(dish);
            }
        }*/
        orderDAO.saveAndFlush(order);
    }

    @Transactional
    @Override
    public void makeAdmin(int userId) {
        User user = userDAO.getOne(userId);
        Set<Role> roles = user.getRoles();
        roles.add(roleDAO.findOne(1));
        userDAO.saveAndFlush(user);
    }

    @Override
    public int getMoney(User user) {
        return user.getMoney();
    }

    /*@Override
    public String getRoleByUsername(String username) {
        User user = userDAO.findByUsername(username);
        if (hasRoleAdmin(user.getRoles())) {
            return "ROLE_ADMIN";
        } else
            return "ROLE_CLIENT";

    }*/


    private boolean hasRoleAdmin(Set<Role> roles) {
        for (Role role : roles) {
            if (role.getName().equals("ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }
}
