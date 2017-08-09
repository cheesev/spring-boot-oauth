package com.example.controller;

import com.example.entity.OauthClientDetails;
import com.example.repository.OauthClientDetailsRepository;
import com.example.service.OauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("oauth")
public class OauthController {

    @Autowired
    OauthClientDetailsService oauthClientDetailsService;

    @RequestMapping(value = "/header", method = RequestMethod.POST)
    public String headerAuthorization(@RequestParam("X-Site-Client-Id") String clientId,
                                      @RequestParam("X-Site-Client-Secret") String clientSecret) {

        String result = "N";

        OauthClientDetails oauthClientDetails
                = oauthClientDetailsService.getOauthClientDetails(clientId, clientSecret);

        if (oauthClientDetails != null) {
            result = "Y";
        }

        return result;
    }
}
