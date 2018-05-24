package com.wedo.OMS.service;

import com.wedo.OMS.entity.User;
import com.wedo.OMS.exception.AttendanceNotFoundException;
import com.wedo.OMS.exception.PasswordIncorrectException;
import com.wedo.OMS.exception.TaskNotFoundException;
import com.wedo.OMS.exception.UserNotFoundException;

import java.sql.Timestamp;
import java.util.List;

public interface UserService {
    /**
     * 用户登陆
     * @param user
     * @return
     */
    User login(User user) throws UserNotFoundException, PasswordIncorrectException;

    /**
     * 根据用户ID获取用户信息
     * @param userId
     * @return
     */
    User getUserByUserId(long userId) throws UserNotFoundException;

    /**
     * 签到
     * @param userId
     * @param taskId
     * @param dateTime
     * @return
     */
    User signin(long userId, long taskId, Timestamp dateTime) throws UserNotFoundException, TaskNotFoundException;

    /**
     * 签退，包括提交考勤日志
     * @param userId
     * @param taskId
     * @param dateTime
     * @return
     */
    User signout(long userId, long taskId, Timestamp dateTime) throws UserNotFoundException, TaskNotFoundException, AttendanceNotFoundException;

    /**
     * 获取所有人脸信息
     * @return
     */
    List<String> getUserFaces();
}
