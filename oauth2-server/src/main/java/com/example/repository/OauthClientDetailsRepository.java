package com.example.repository;

import com.example.entity.OauthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthClientDetailsRepository extends JpaRepository<OauthClientDetails, String> {

    OauthClientDetails findByClientIdAndClientSecret(String clientId, String clientSecret);

}
