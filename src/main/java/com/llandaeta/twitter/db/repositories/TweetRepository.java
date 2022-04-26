package com.llandaeta.twitter.db.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.llandaeta.twitter.db.entities.TweetEntity;

@Repository
public interface TweetRepository extends JpaRepository<TweetEntity, Long> {

    List<TweetEntity> findByUsernameAndValidTrue(String username);
}
