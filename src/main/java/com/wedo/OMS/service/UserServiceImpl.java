package com.wedo.OMS.service;

import com.wedo.OMS.entity.Attendance;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.entity.User;
import com.wedo.OMS.repository.AttendanceRepository;
import com.wedo.OMS.repository.TaskRepository;
import com.wedo.OMS.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Base64;
import java.util.Base64.Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private AttendanceRepository attendanceRepository;
    private TaskRepository taskRepository;
    public UserServiceImpl(UserRepository userRepository,AttendanceRepository attendanceRepository,TaskRepository taskRepository){
        this.userRepository = userRepository;
        this.attendanceRepository=attendanceRepository;
        this.taskRepository=taskRepository;
    }

    /*MD5密码加密处理*/
    public String EncoderByMd5(String str) throws NoSuchAlgorithmException,UnsupportedEncodingException{
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        Encoder base64Encoder = Base64.getEncoder();
        String newstr=base64Encoder.encodeToString(md5.digest(str.getBytes("utf-8")));
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
        Attendance attendance =new Attendance();
        User user =userRepository.findUserById(userId);
        Task task =taskRepository.findTaskById(taskId);
        attendance.setUser(user);
        attendance.setTask(task);
        attendance.setBeginTime(dateTime);
        attendanceRepository.save(attendance);
        return userRepository.findUserById(userId);
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
        User user=userRepository.findUserById(userId);
        Task task =taskRepository.findTaskById(taskId);
        Attendance attendance=attendanceRepository.findAttendanceByUserAndAndTask(user,task);
        attendance.setEndTime(dateTime);
        attendanceRepository.save(attendance);
        return userRepository.findUserById(userId);
    }
}
