package com.example.controller;

import com.example.entity.OauthClientDetails;
import com.example.service.OauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("oauth")
public class OauthController {

    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;

    @RequestMapping(value = "/header", method = RequestMethod.GET)
    public String headerAuthorization(@RequestHeader("X-Site-Client-Id") String clientId,
                                      @RequestHeader("X-Site-Client-Secret") String clientSecret) {

        String result = "N";

        OauthClientDetails oauthClientDetails
                = oauthClientDetailsService.getOauthClientDetails(clientId, clientSecret);

        if (oauthClientDetails != null) {
            result = "Y";
        }

        return result;
    }
}
