package com.mycompany.craftdemo.service;

import com.mycompany.craftdemo.model.Tweet;

import java.util.List;

public interface TweetService {
     /**
      * 
      * Returns user's tweets
      * @param userId
      * @return
      */
     List<Tweet> getUserFeed(int userId, int page, int size) ;

     /**
      * returns all the tweets from the users following(home timeline)
      * @param userId
      * @return
      */
     List<Tweet> getAllFeed(int userId, int page, int size) ;

     /**
      * Returns the tweet y Id
      * @param id
      * @return
      */
     Tweet getFeedById(int id);

     /**
      * 
      * @param tweet
      * @param userId
      */
     void saveOrUpdate(Tweet tweet, int userId) ;

     /**
      * 
      * @param userId
      * @param tweetId
      */
      
     void delete(int userId, int tweetId) ;
}
