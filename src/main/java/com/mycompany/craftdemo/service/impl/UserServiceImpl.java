package com.mycompany.craftdemo.service.impl;

import com.mycompany.craftdemo.Exception.CraftDemoException;
import com.mycompany.craftdemo.dao.UserRepository;
import com.mycompany.craftdemo.model.User;
import com.mycompany.craftdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUser(int userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void saveOrUpdate(User user) {
        userRepository.save(user);
    }

    @Override
    public User delete(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new CraftDemoException("user doesn't exist id : " + id);
        }
        if (!optionalUser.get().isActive()) {
            throw new CraftDemoException("User is already deleted : " + id);
        }
        User user = optionalUser.get();
        user.setActive(false);
        userRepository.save(user);
        return user;
    }
}
