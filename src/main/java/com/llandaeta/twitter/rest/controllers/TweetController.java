package com.llandaeta.twitter.rest.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.llandaeta.twitter.core.model.TweetModel;
import com.llandaeta.twitter.core.services.TweetsService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = { "/tweets" })
public class TweetController {
    private TweetsService tweetsService;

    /**
     * method that returns a list with all saved tweets
     **/
    @GetMapping("")
    ResponseEntity<List<TweetModel>> getAllTweets() {

        return new ResponseEntity<>(tweetsService.findAll(), HttpStatus.OK);

    }

    /**
     * method that updates a tweet as validated
     * @param id
     * @return List of tweets of type TweetModel
     **/
    @PatchMapping(path = "/{id}/valid")
    ResponseEntity<Object> valid(@PathVariable Long id) {

        TweetModel tweet = tweetsService.findById(id);

        if (tweet != null) {

            return new ResponseEntity<>(tweetsService.validTweet(tweet), HttpStatus.OK);

        } else {

            return new ResponseEntity<>("Tweet not found", HttpStatus.NOT_FOUND);

        }

    }

    /**
     * method that returns a list with all validated tweets of a username
     * @param username
     **/
    @GetMapping("/validTweets/{username}")
    ResponseEntity<List<TweetModel>> validTweetsByUser(@PathVariable String username) {

        return new ResponseEntity<>(tweetsService.findValidByUsername(username.replace("_", " ")), HttpStatus.OK);

    }

    /**
     * method that returns a list with of hashtags more used
     **/
    @GetMapping("/hashtags")
    ResponseEntity<Set<String>> getHashTags() {

        return new ResponseEntity<>(tweetsService.getTopHashTags(), HttpStatus.OK);

    }

}