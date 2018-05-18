package com.wedo.OMS.service;

import com.wedo.OMS.entity.User;
import com.wedo.OMS.repository.UserRepository;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /*MD5密码加密处理*/
    public String EncoderByMd5(String str) throws NoSuchAlgorithmException,UnsupportedEncodingException{
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String newstr=base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }

    /**
     * 用户登陆
     * @param user
     * @return
     */
    @Override
    public User login(User user) throws NoSuchAlgorithmException,UnsupportedEncodingException{
        User userRecorded = userRepository.findUserById(user.getId());
        String passwordRecorded = userRecorded.getPassword();
        String passwordInput = user.getPassword();
        passwordInput = EncoderByMd5(passwordInput);
        if (passwordRecorded.equals(passwordInput)){
            return userRecorded;
        }
        else
            return null;
    }

    /**
     * 根据用户ID获取用户信息
     * @param userId
     * @return
     */
    @Override
    public User getUserByUserId(Long userId) {
        return userRepository.findUserById(userId);
    }

    /**
     * 签到
     * @param userId
     * @param taskId
     * @param dateTime
     * @return
     */
    @Override
    public User signin(Long userId, Long taskId, Timestamp dateTime) {
        return null;
    }

    /**
     * 签退，包括提交考勤日志
     * @param userId
     * @param taskId
     * @param dateTime
     * @return
     */
    @Override
    public User signout(Long userId, Long taskId, Timestamp dateTime) {
        return null;
    }
}
