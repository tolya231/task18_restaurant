package com.epam.service;

import com.epam.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void save(User user);

    //void removeUser(Integer id);

    //List<User> getAllUsers();

    User findByUsername(String username);

    //String getRoleByUsername(String username);
}
