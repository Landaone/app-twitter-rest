package com.llandaeta.twitter.core.services;

import java.util.List;
import java.util.Set;

import com.llandaeta.twitter.core.model.TweetModel;
import com.llandaeta.twitter.db.entities.TweetEntity;

/**
 * Interface where are exposed the methods to be consumed by the rest controller
 */
public interface TweetsService {
    TweetModel save(TweetEntity tweet);
    List<TweetModel> findAll();
    TweetModel findById(Long id);
    List<TweetModel> findValidByUsername(String replace);
    TweetModel validTweet(TweetModel tweet);
    Set<String> getTopHashTags();
}
