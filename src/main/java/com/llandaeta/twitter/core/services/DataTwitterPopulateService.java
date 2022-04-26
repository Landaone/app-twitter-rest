package com.llandaeta.twitter.core.services;

import java.nio.charset.StandardCharsets;

import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import com.llandaeta.twitter.core.config.PopulateProperties;
import com.llandaeta.twitter.core.converters.TweetModelToEntity;
import com.llandaeta.twitter.core.model.TweetModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStreamFactory;

/**
 * service that connects with the twitter API to obtain the data
 */
@Slf4j
@Service
@AllArgsConstructor
public class DataTwitterPopulateService {

    private PopulateProperties properties;
    private TweetsService tweetsService;
    private TweetModelToEntity modelToEntity;

    public void streamingAPI() {

        var twitterStream = new TwitterStreamFactory().getInstance();

        StatusListener listener = new StatusListener() {

            @Override
            public void onStatus(Status status) {

                String lang = status.getLang();

                int userFollowers = status.getUser().getFollowersCount();

                try {

                    if (properties.getLanguages().contains(lang) && userFollowers > properties.getMinFollowers()) {

                        var text = new String(status.getText().getBytes(StandardCharsets.UTF_8),
                            StandardCharsets.UTF_8);

                        var username = new String(status.getUser().getName().getBytes(StandardCharsets.UTF_8),
                            StandardCharsets.UTF_8);

                        Point location = null;

                        if (status.getGeoLocation() != null) {

                            location = new Point(status.getGeoLocation().getLatitude(), status.getGeoLocation().getLongitude());
                        }

                        tweetsService.save(modelToEntity.convert(getTweetModel(text, username, location, false)));

                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }

            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            }

            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            }

            public void onScrubGeo(long userId, long upToStatusId) {
            }

            public void onStallWarning(StallWarning warning) {
            }

            public void onException(Exception ex) {

                ex.printStackTrace();
            }
        };

        twitterStream.addListener(listener);

        twitterStream.sample();
    }

    private TweetModel getTweetModel(String text, String user, Point location, boolean valid) {
        return TweetModel.builder()
            .username(user)
            .text(text)
            .location(location)
            .valid(valid)
            .build();
    }

}
