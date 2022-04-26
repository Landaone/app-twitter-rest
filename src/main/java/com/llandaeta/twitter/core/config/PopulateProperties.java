package com.llandaeta.twitter.core.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class used to get the values from the properties file
 */
@Data
@Configuration
@ConfigurationProperties("twitter")
@NoArgsConstructor
public class PopulateProperties {

    private Integer minFollowers;

    private  List<String> languages;

    private  Integer numHashtags;

}
