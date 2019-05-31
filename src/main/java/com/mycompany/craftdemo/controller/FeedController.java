package com.mycompany.craftdemo.controller;

import com.mycompany.craftdemo.model.Tweet;
import com.mycompany.craftdemo.model.View;
import com.mycompany.craftdemo.service.TweetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/{userId}/feed")
public class FeedController {

    final TweetService feedService;

    @Autowired
    public FeedController(final TweetService feedService){
        this.feedService =feedService;
    }

    Logger logger = LoggerFactory.getLogger(FeedController.class);

    @GetMapping(produces = "application/json")
    public List<Tweet> timeLine(@PathVariable("userId") Integer userId,
                                @RequestParam(name = "minorversion") String minorVersion,
                                @RequestParam(name = "view") String view, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        logger.info("view ="+view);
        if (View.HOME.name().equalsIgnoreCase(view)) {
            return feedService.getAllFeed(userId, page, size);
        } else if (View.USER.name().equalsIgnoreCase(view)) {
            return feedService.getUserFeed(userId, page, size);
        }
        return null;
    }

}
