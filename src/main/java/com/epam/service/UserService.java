package com.epam.service;

import com.epam.model.Order;
import com.epam.model.User;

public interface UserService {

    void save(User user);

    //void removeUser(Integer id);

    //List<User> getAllUsers();

    Order getOrderByUsername(String username);

    User findByUsername(String username);

    boolean isAdmin(User user);
    //String getRoleByUsername(String username);
}
