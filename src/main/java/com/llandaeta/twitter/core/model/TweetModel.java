package com.llandaeta.twitter.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.geo.Point;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class TweetModel {

    private Long id;

    private String username;
    private String text;

    private Point location;

    private Boolean valid;


    public List<String> hashTags() {

        String text = this.text;

        var pattern = Pattern.compile("#(\\S+)");

        var mat = pattern.matcher(text);

        List<String> hashtags = new ArrayList<>();

        while (mat.find()) {
            hashtags.add(mat.group(1));
        }

        return hashtags;
    }
}
