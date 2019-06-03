package com.mycompany.craftdemo.dao;

import com.mycompany.craftdemo.model.Tweet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends CrudRepository<Tweet, Integer>, PagingAndSortingRepository<Tweet, Integer> {
    
    List<Tweet> findByUserIdIsInOrderByCreateTimeDesc(List<Integer> userIds, Pageable pageable);

    List<Tweet> findAllByUserIdOrderByCreateTimeDesc(Integer userIds, Pageable pageable);
   
    List<Tweet> findAllByOrderByCreateTimeDesc(Pageable pageable);
   
    List<Tweet> findAllByMessageContainingOrderByCreateTimeDesc(String tweet, Pageable pageable);
}

