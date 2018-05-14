package com.wedo.OMS.service;

import com.wedo.OMS.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 用户登陆
     * @param user
     * @return
     */
    User signIn(User user);


    User signUp(User user);

    User getUserByUserId(Long userId);

    List<User> getByUserId(Long userId);

}
