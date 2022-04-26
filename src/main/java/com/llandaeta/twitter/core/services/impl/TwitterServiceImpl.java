package com.llandaeta.twitter.core.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.llandaeta.twitter.core.config.PopulateProperties;
import com.llandaeta.twitter.core.converters.TweetEntityToModel;
import com.llandaeta.twitter.core.converters.TweetModelToEntity;
import com.llandaeta.twitter.core.exceptions.TweetNotFoundException;
import com.llandaeta.twitter.core.model.TweetModel;
import com.llandaeta.twitter.core.services.TweetsService;
import com.llandaeta.twitter.db.entities.TweetEntity;
import com.llandaeta.twitter.db.repositories.TweetRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class TwitterServiceImpl implements TweetsService {

    private PopulateProperties properties;
    private final TweetRepository tweetRepository;
    private final TweetEntityToModel  entityToModel;
    private final TweetModelToEntity modelToEntity;


    public List<TweetModel> findAll() {
        List<TweetEntity> allTweetsList = tweetRepository.findAll();

        return allTweetsList.stream()
            .map(entityToModel::convert)
            .collect(Collectors.toList());
    }

    public List<TweetModel> findValidByUsername(String username) {
        List<TweetEntity> tweetByUserList = tweetRepository.findByUsernameAndValidTrue(username);

        return tweetByUserList.stream()
            .map(entityToModel::convert)
            .collect(Collectors.toList());

    }
    public TweetModel save(TweetEntity tweetEntity) {
       return entityToModel.convert(tweetRepository.save(tweetEntity));
    }

    public TweetModel findById(Long id) throws TweetNotFoundException {
        Optional<TweetEntity> tweet = tweetRepository.findById(id);

        return tweet
            .map(entityToModel::convert)
            .orElseThrow(() -> new TweetNotFoundException("tweet Not found with id: " + id));

    }

    public Set<String> getTopHashTags() {

        List<TweetModel> tweetModelList = this.findAll();

        List<String> hashTags = new ArrayList<>();

        tweetModelList.forEach(t -> hashTags.addAll(t.hashTags()));

        java.util.Map<String, Long> hashTagsGroup = hashTags.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        java.util.Map<String, Long> top = hashTagsGroup.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(properties.getNumHashtags())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return top.keySet();
    }

    public TweetModel validTweet(TweetModel tweet) {

        tweet.setValid(true);

        return this.save(modelToEntity.convert(tweet));

    }

}
