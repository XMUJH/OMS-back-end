package com.wedo.OMS.repository;

import com.wedo.OMS.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void addUser() {
        User user = new User();
        user.setName("1");
        user.setPassword("1");
        user.setRole(null);
        userRepository.save(user);
    }
}