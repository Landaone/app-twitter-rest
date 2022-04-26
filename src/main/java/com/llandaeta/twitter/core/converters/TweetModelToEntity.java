package com.llandaeta.twitter.core.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.llandaeta.twitter.core.model.TweetModel;
import com.llandaeta.twitter.db.entities.TweetEntity;

/**
 * Spring converter implementation to pass data from model class to entity class
 */
@Component
public class TweetModelToEntity implements Converter<TweetModel, TweetEntity> {

    @Override
    public TweetEntity convert(TweetModel tweetModel) {
        return TweetEntity.builder()
            .id(tweetModel.getId())
            .username(tweetModel.getUsername())
            .text(tweetModel.getText())
            .location(tweetModel.getLocation())
            .valid(tweetModel.getValid())
            .build();
    }

}
