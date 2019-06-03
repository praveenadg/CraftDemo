package com.mycompany.craftdemo.service.impl;

import com.mycompany.craftdemo.Exception.CraftDemoException;
import com.mycompany.craftdemo.dao.FollowRepository;
import com.mycompany.craftdemo.dao.UserRepository;
import com.mycompany.craftdemo.model.Follow;
import com.mycompany.craftdemo.model.User;
import com.mycompany.craftdemo.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FollowServiceImpl implements FollowerService {

    private final FollowRepository followerRepository;
    private final UserRepository userRepository;
    
    @Autowired
    public FollowServiceImpl(final FollowRepository followerRepository, final UserRepository userRepository){
     this.followerRepository = followerRepository;
     this.userRepository = userRepository;
    }
   
    @Override
    public void follow(int userId, int following) {
        validate(userId, following);
        if(followerRepository.findAllByUserIdAndFollowingUser(userId, following).size() > 0){
            throw new CraftDemoException(" You are already following the user : " + following);
        }
        Follow follow = new Follow();
        follow.setUserId(userId);
        follow.setFollowingUser(following);
        followerRepository.save(follow);
        
    }
    
    @Override
    @Transactional
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

    /**
     * Validates userId and followUnfollowId are not same.
     * Validates the user present and active.
     * Validates the followUnfollow user present and active.
     * @param userId
     * @param followUnfollowId
     */
    private void validate(int userId, int followUnfollowId){
        if(userId == followUnfollowId){
            throw new CraftDemoException("User and follow/unfollow user cannot be same");
        }
        Optional<User> optionalFollowUser =userRepository.findById(followUnfollowId);
        if(!optionalFollowUser.isPresent() && !optionalFollowUser.get().isActive()){
            throw new CraftDemoException("User not found to follow/unfollow :" + followUnfollowId);
        }
        Optional<User> optionalUser =userRepository.findById(userId);
        if(!optionalUser.isPresent() && !optionalUser.get().isActive()){
            throw new CraftDemoException(" user not found : " + userId);
        }
    }
}
