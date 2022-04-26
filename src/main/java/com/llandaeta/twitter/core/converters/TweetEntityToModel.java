package com.llandaeta.twitter.core.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.llandaeta.twitter.core.model.TweetModel;
import com.llandaeta.twitter.db.entities.TweetEntity;

/**
 * Spring converter implementation to pass data from entity class to model class
 */
@Component
public class TweetEntityToModel  implements Converter<TweetEntity, TweetModel> {

    @Override
    public TweetModel convert(TweetEntity tweetEntity){

        return TweetModel.builder()
            .id(tweetEntity.getId())
            .username(tweetEntity.getUsername())
            .text(tweetEntity.getText())
            .location(tweetEntity.getLocation())
            .valid(tweetEntity.getValid())
            .build();
    }
}
