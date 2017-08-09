package com.example.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class OauthClientDetails {

    @Id
    String clientId;
    String resourceIds;
    String clientSecret;
    String scope;
    String authorized_grant_types;
    String webServerRedirectUri;
    String authorities;
    int accessTokenValidity;
    int refreshTokenValidity;
    String additionalInformation;
    String autoapprove;

    public OauthClientDetails() {}
}
