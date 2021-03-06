package com.mycompany.craftdemo.service.impl;

import com.mycompany.craftdemo.Exception.CraftDemoException;
import com.mycompany.craftdemo.dao.TweetRepository;
import com.mycompany.craftdemo.model.Tweet;
import com.mycompany.craftdemo.model.User;
import com.mycompany.craftdemo.service.FollowerService;
import com.mycompany.craftdemo.service.TweetService;
import com.mycompany.craftdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;

    private final FollowerService followerService;

    private final UserService userService;

    @Autowired
    public TweetServiceImpl(final TweetRepository tweetRepository, final FollowerService followerService, final UserService userService){
        this.tweetRepository = tweetRepository;
        this.followerService = followerService;
        this.userService = userService;
    }
    
    public List<Tweet> getAllFeed(int userId, int page, int size) {
        List<Tweet> feed = new ArrayList<>();
        List<Integer>  followingList = followerService.getFollowing(userId).stream().filter(user->user.isActive()).mapToInt(User::getUserId).boxed().collect(Collectors.toList());
        /// find by user id
        tweetRepository.findByUserIdIsInOrderByCreateTimeDesc(followingList, PageRequest.of(page, size)).forEach(tweet -> feed.add(tweet));
        return feed;
        
    }

    public List<Tweet> search(String message, int page, int size) {
        List<Tweet> feed = new ArrayList<>();
        tweetRepository.findAllByMessageContainingOrderByCreateTimeDesc(message, PageRequest.of(page, size)).forEach(tweet -> feed.add(tweet));
        return feed;
    }

    public List<Tweet> getUserFeed(int userId, int page, int size) {
        return tweetRepository.findAllByUserIdOrderByCreateTimeDesc(userId,PageRequest.of(page, size));
    }

    public Tweet getFeedById(int id) {
        return tweetRepository.findById(id).get();
    }

    public Tweet saveOrUpdate(Tweet tweet, int userId) {
        //validate for create
        if(tweet.getUserId() != 0 && userId != tweet.getUserId()){
            throw new CraftDemoException("Tweet id : "+tweet.getTweetId() +" doesn't exist for user : "+userId);
        }
        //validate for update
        tweetRepository.findById(tweet.getTweetId()).ifPresent(dbTweet -> {
            if(dbTweet.getUserId() != userId){
                throw new CraftDemoException("Tweet id : "+tweet.getTweetId() +" doesn't exist for user : "+userId); 
            }});
        
        //validate the user
        Optional<User> optionalUser =userService.getUser(userId);
        if(!optionalUser.isPresent() && !optionalUser.get().isActive()) {
            throw new CraftDemoException("User doesn't exist. UserId : "+userId);
        }
        tweet.setUserId(userId);
        return tweetRepository.save(tweet);
    }

    /**
     * Validate the tweet is present and tweet belongs to the user
     * @param userId
     * @param tweetId
     */
    public void delete(int userId, int tweetId) {
        Optional<Tweet> tweet = tweetRepository.findById(tweetId);
        if(tweet.isPresent()){
            if(tweet.get().getUserId() == userId)
            tweetRepository.deleteById(tweetId);
            else
                throw new CraftDemoException("Tweet id : "+tweetId +"doesn't exist for user :"+userId);
        } else {
            throw new CraftDemoException("Tweet doesn't exist. tweetId : "+tweetId);
        }
        
    }
}
