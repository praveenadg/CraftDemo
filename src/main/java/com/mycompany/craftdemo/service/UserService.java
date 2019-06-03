package com.mycompany.craftdemo.service;

import com.mycompany.craftdemo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getUser(int userId);

    List<User> getAllUsers();

    User getUserById(int id);

    /**
     * Create or update a used based on Id
     * @param user
     */
    User saveOrUpdate(User user);

    /**
     * Delete a user by id
     * @param id
     */
    User delete(int id);
}
