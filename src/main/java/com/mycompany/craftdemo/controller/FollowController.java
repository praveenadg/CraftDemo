package com.mycompany.craftdemo.controller;

import com.mycompany.craftdemo.Exception.CraftDemoException;
import com.mycompany.craftdemo.model.Operation;
import com.mycompany.craftdemo.model.User;
import com.mycompany.craftdemo.service.FollowerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/{userId}")
public class FollowController {


    private final FollowerService followerService;
   
    @Autowired
    public FollowController (final FollowerService followerService){
        this.followerService=followerService;
    }

    Logger logger = LoggerFactory.getLogger(FollowController.class);

    @PostMapping(produces = "application/json")
    public String follow(@PathVariable("userId") int userId,
                       @RequestParam(name = "minorversion") String minorVersion, @RequestBody User user, @RequestParam(name = "opt") String operation) {
        logger.info("operation ="+operation);
        if(Operation.FOLLOW.name().equalsIgnoreCase(operation)){
            followerService.follow(userId, user.getUserId());
        } else if(Operation.UNFOLLOW.name().equalsIgnoreCase(operation)) {
            followerService.unFollow(userId, user.getUserId());
        } else {//error
            throw new CraftDemoException("Invalid operation: "+operation); 
        }
        return "Success";
    }

    @GetMapping(produces = "application/json")
    @RequestMapping(path = "/following")
    public List<User> getFollowers(@PathVariable("userId") int userId,
                                   @RequestParam(name = "minorversion") String minorVersion) {

        return followerService.getFollowing(userId);
    }
}
