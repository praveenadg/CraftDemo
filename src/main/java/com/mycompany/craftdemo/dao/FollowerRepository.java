package com.mycompany.craftdemo.dao;

import com.mycompany.craftdemo.model.Follow;
import com.mycompany.craftdemo.model.Tweet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerRepository extends CrudRepository<Follow, Integer> {
    
    void deleteByFollowingUserAndUserId(int followingUser, int user);

    List<Follow> findAllByUserId(int userId);
}
