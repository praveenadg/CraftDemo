package com.mycompany.craftdemo.service;

import com.mycompany.craftdemo.model.User;

import java.util.List;

public interface FollowerService {

    /**
     * follow the user.
     * Throws error if both userId and following are same
     * @param userId
     * @param following
     */
    void follow(int userId, int following);

    /**
     * Unfollow the user.
     * Throws error if both userId and unFollowing are same
     * @param userId
     * @param unFollowing
     */
    void unFollow(int userId, int unFollowing);

    /**
     * Returns the list of users followed by passed in userId
     * @param userId
     * @return
     */
    List<User> getFollowing(int userId);
}
