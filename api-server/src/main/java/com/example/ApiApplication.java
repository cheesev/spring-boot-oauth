package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

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
                        .requestMatchers(new HeaderRequestMatcher()).permitAll()
                        .antMatchers("/members/**").access("#oauth2.hasScope('read')")
                        .anyRequest().authenticated();
            }
        };
    }

    @Bean
    public CommandLineRunner commandLineRunner(MemberRepository memberRepository) {
        return args -> {
            memberRepository.save(new Member("이철수", "chulsoo", "test111"));
            memberRepository.save(new Member("김정인", "jungin11", "test222"));
            memberRepository.save(new Member("류정우", "jwryu991", "test333"));
        };
    }

    static class HeaderRequestMatcher implements RequestMatcher {
        static final RestTemplate restTemplate = new RestTemplate();

        @Override
        public boolean matches(HttpServletRequest request) {
            String uri = request.getRequestURI();

            // 리소스 서버에서 사용할 URI를 명시한다. (패턴으로 받기가 가능해야함.)
            if (!uri.equals("/members")) return false;

            String clientId = request.getHeader("X-Site-Client-Id");
            String clientSecret = request.getHeader("X-Site-Client-Secret");

            HttpHeaders headers = new HttpHeaders();
            headers.add("X-Site-Client-Id", clientId);
            headers.add("X-Site-Client-Secret", clientSecret);
            HttpEntity entity = new HttpEntity(headers);

            ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8080/oauth/header", HttpMethod.GET, entity, String.class);


            return ("Y".equals(responseEntity.getBody()));
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}

