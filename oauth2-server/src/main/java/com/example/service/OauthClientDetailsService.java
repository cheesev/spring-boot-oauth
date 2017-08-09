package com.example.service;

import com.example.entity.OauthClientDetails;
import com.example.repository.OauthClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OauthClientDetailsService {

    @Autowired
    OauthClientDetailsRepository oauthClientDetailsRepository;

    public OauthClientDetails getOauthClientDetails(String clientId, String clientSecret) {
        return oauthClientDetailsRepository.findByClientIdAndClientSecret(clientId, clientSecret);
    }
}
