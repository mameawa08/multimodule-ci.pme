package com.administration.config.oauth.repository;

import com.administration.config.oauth.entities.OauthAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OAuthAccessTokenRepository extends JpaRepository<OauthAccessToken, Integer> {

    @Query("DELETE FROM OauthAccessToken oat WHERE oat.userName = :username")
    void deleteOauthAccessTokenByUserName(String username);

    @Query("SELECT oat FROM OauthAccessToken oat WHERE oat.userName = :username")
    List<OauthAccessToken> findByUserName(String username);
}
