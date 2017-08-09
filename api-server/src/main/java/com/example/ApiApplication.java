package com.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@EnableResourceServer
@SpringBootApplication
public class ApiApplication {
    @Bean
    public ResourceServerConfigurerAdapter resourceServerConfigurerAdapter() {
        return new ResourceServerConfigurerAdapter() {
            @Override
            public void configure(HttpSecurity http) throws Exception {

                http.headers().frameOptions().disable();

                http.authorizeRequests()
                        .antMatchers("/members").permitAll()
                        .antMatchers("/members/**").access("#oauth2.hasScope('read')")
                        .anyRequest().authenticated();
            }
        };
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner commandLineRunner(MemberRepository memberRepository) {
        return args -> {
            memberRepository.save(new Member("이철수", "chulsoo", "test111"));
            memberRepository.save(new Member("김정인", "jungin11", "test222"));
            memberRepository.save(new Member("류정우", "jwryu991", "test333"));
        };
    }

    private static class HeaderRequestMatcher implements RequestMatcher {

        @Autowired
        RestTemplate restTemplate;

        @Override
        public boolean matches(HttpServletRequest request) {
            String clientId = request.getHeader("X-Site-Client-Id");
            String clientSecret = request.getHeader("X-Site-Client-Secret");

            String authResult = restTemplate.getForObject("http://localhost:8080/authorization/header", String.class);

            return ("Y".equals(authResult));
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}

