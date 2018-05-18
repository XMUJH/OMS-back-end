package com.wedo.OMS.service;

import com.wedo.OMS.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public com.wedo.OMS.entity.User login(com.wedo.OMS.entity.User user) {
        return null;
    }

    @Override
    public com.wedo.OMS.entity.User getUserByUserId(Long userId) {
        return null;
    }

    @Override
    public com.wedo.OMS.entity.User signin(Long userId, Long taskId, Timestamp dateTime) {
        return null;
    }

    @Override
    public com.wedo.OMS.entity.User signout(Long userId, Long taskId, Timestamp dateTime) {
        return null;
    }
}
