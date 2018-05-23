package com.wedo.OMS.service;

import com.wedo.OMS.entity.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.List;

public interface UserService {
    /**
     * 用户登陆
     * @param user
     * @return
     */
    User login(User user)throws NoSuchAlgorithmException,UnsupportedEncodingException;

    /**
     * 根据用户ID获取用户信息
     * @param userId
     * @return
     */
    User getUserByUserId(long userId);

    /**
     * 签到
     * @param userId
     * @param taskId
     * @param dateTime
     * @return
     */
    User signin(long userId, long taskId, Timestamp dateTime);

    /**
     * 签退，包括提交考勤日志
     * @param userId
     * @param taskId
     * @param dateTime
     * @return
     */
    User signout(long userId, long taskId, Timestamp dateTime);

    /**
     * 获取所有人脸信息
     * @return
     */
    List<String> getUserFaces();
}
