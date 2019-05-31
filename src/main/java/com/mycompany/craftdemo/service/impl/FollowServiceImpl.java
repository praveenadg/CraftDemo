package com.mycompany.craftdemo.service.impl;

import com.mycompany.craftdemo.Exception.CraftDemoException;
import com.mycompany.craftdemo.dao.FollowerRepository;
import com.mycompany.craftdemo.dao.UserRepository;
import com.mycompany.craftdemo.model.Follow;
import com.mycompany.craftdemo.model.User;
import com.mycompany.craftdemo.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowServiceImpl implements FollowerService {

    private final FollowerRepository followerRepository;
    private final UserRepository userRepository;
    
    @Autowired
    public FollowServiceImpl(final FollowerRepository followerRepository, final UserRepository userRepository){
     this.followerRepository = followerRepository;
     this.userRepository = userRepository;
    }
   
    @Override
    public void follow(int userId, int following) {
        validate(userId, following);
        Follow follow = new Follow();
        follow.setUserId(userId);
        follow.setFollowingUser(following);
        followerRepository.save(follow);
        
    }

    private void validate(int userId, int followId){
        if(userId == followId){
            throw new CraftDemoException("User and follow/unfollow user cannot be same");
        }
        if(!userRepository.findById(followId).isPresent()){
            throw new CraftDemoException("User not found ti follow/unfollow" + followId);
        }
        if(!userRepository.findById(userId).isPresent()){
            throw new CraftDemoException(" user not found" + userId);
        }
    }
    @Override
    public void unFollow(int userId, int unFollowing) {
        validate(userId, unFollowing);
        followerRepository.deleteByFollowingUserAndUserId(unFollowing, userId);
    }

    @Override
    public List<User> getFollowing(int userId) {
        List<Integer> followUser = new ArrayList<>();
        List<User> followUserList = new ArrayList<>();
        followerRepository.findAllByUserId(userId).forEach(follow->{
            followUser.add(follow.getFollowingUser());
        });
         userRepository.findAllById(followUser).forEach(user->followUserList.add(user));
         return followUserList;
        
    }


}
