package com.wedo.OMS.repository;

import com.wedo.OMS.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void addUser() {
        User user = new User();
        user.setName("1");
        user.setPassword("1");
        userRepository.save(user);
    }
}