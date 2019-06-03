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
      * Searches the given message in all tweets
      * @param message
      * @param page
      * @param size
      * @return
      */
     List<Tweet> search(String message, int page, int size) ;

     /**
      * Returns the tweet by Id
      * @param id
      * @return
      */
     Tweet getFeedById(int id);

     /**
      * 
      * @param tweet
      * @param userId
      */
     Tweet saveOrUpdate(Tweet tweet, int userId) ;

     /**
      * 
      * @param userId
      * @param tweetId
      */
      
     void delete(int userId, int tweetId) ;
}
