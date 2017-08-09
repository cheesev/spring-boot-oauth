package com.example.repository;

import com.example.OAuth2Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OAuth2Application.class )
public class UserRepositoryTest {

//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void selectUserTest() {
//        User user = userRepository.findOneByUsername("test");
//        assertEquals(user.getUsername() , "test");
//    }

}
