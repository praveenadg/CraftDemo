package com.mycompany.craftdemo.dao;

import com.mycompany.craftdemo.model.Follow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends CrudRepository<Follow, Integer> {
    
    void deleteByFollowingUserAndUserId(int followingUser, int userId);

    List<Follow> findAllByUserId(int userId);

    List<Follow> findAllByUserIdAndFollowingUser(int userId, int followingUser);
}
