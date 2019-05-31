package com.mycompany.craftdemo.controller;

import com.mycompany.craftdemo.model.User;
import com.mycompany.craftdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/user")
public class UserController {

    private final UserService userService;
    
    @Autowired
    public UserController(final UserService userService){
        this.userService = userService;
    }

    @DeleteMapping(path = "/{userId}", produces = "application/json")
    public User delete(@RequestParam(name = "minorversion") String minorVersion,
                       @PathVariable("userId") int userId) {

        return userService.delete(userId);
    }

    @PostMapping(produces = "application/json")
    public User create(@RequestParam(name = "minorversion") String minorVersion, @RequestBody User user) {
        userService.saveOrUpdate(user);
        return user;
    }

    @PutMapping(produces = "application/json")
    public User update(@RequestParam(name = "minorversion") String minorVersion,
                       @RequestBody User user) {
        userService.saveOrUpdate(user);
        return user;
    }
}
