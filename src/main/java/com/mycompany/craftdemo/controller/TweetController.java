package com.mycompany.craftdemo.controller;

import com.mycompany.craftdemo.model.Tweet;
import com.mycompany.craftdemo.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/{userId}/tweet")
public class TweetController {

    private final TweetService tweetService;

    @Autowired
    public TweetController(final TweetService tweetService){
        this.tweetService = tweetService;
    }

    @DeleteMapping(path = "/{tweetId}", produces = "application/json")
    public void delete(@PathVariable("userId") int userId,
                       @RequestParam(name = "minorversion") String minorVersion,
                       @PathVariable("tweetId") int tweetId) {

        tweetService.delete(userId, tweetId);
    }

    @PostMapping(produces = "application/json")
    public Tweet create(@PathVariable("userId") Integer userId,
                        @RequestParam(name = "minorversion") String minorVersion, @RequestBody Tweet tweet) {
        tweetService.saveOrUpdate(tweet, userId);
        return tweet;
    }

    @PutMapping(produces = "application/json")
    public Tweet update(@PathVariable("userId") Integer userId,
                        @RequestParam(name = "minorversion") String minorVersion,
                        @RequestBody Tweet tweet) {

        tweetService.saveOrUpdate(tweet, userId);
        return tweet;
    }
}


