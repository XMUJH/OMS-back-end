package com.wedo.OMS.service;

import com.wedo.OMS.entity.Attendance;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.entity.User;
import com.wedo.OMS.exception.AttendanceNotFoundException;
import com.wedo.OMS.exception.PasswordIncorrectException;
import com.wedo.OMS.exception.TaskNotFoundException;
import com.wedo.OMS.exception.UserNotFoundException;
import com.wedo.OMS.repository.AttendanceRepository;
import com.wedo.OMS.repository.TaskRepository;
import com.wedo.OMS.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Base64.Encoder;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AttendanceRepository attendanceRepository;
    private final TaskRepository taskRepository;

    public UserServiceImpl(UserRepository userRepository, AttendanceRepository attendanceRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.attendanceRepository = attendanceRepository;
        this.taskRepository = taskRepository;
    }

    // TODO Should be replaced by a better encryption method
    public String EncoderByMd5(String str) {
        String encryptedPassword = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            Encoder base64Encoder = Base64.getEncoder();
            encryptedPassword = base64Encoder.encodeToString(md5.digest(str.getBytes("utf-8")));
        } catch (Exception e) {
            // Should not happen
            e.printStackTrace();
        }
        return encryptedPassword;
    }

    @Override
    public User login(User user) throws UserNotFoundException, PasswordIncorrectException {
        User userRecorded = userRepository.findUserByAccount(user.getAccount());
        if (userRecorded == null) {
            throw new UserNotFoundException();
        }
        String passwordRecorded = userRecorded.getPassword();
        String passwordInput = user.getPassword();
        //passwordInput = EncoderByMd5(passwordInput);
        if (!passwordRecorded.equals(passwordInput)) {
            throw new PasswordIncorrectException();
        }
        return userRecorded;
    }

    @Override
    public User getUserByUserId(long userId) throws UserNotFoundException {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public User signin(long userId, long taskId, Timestamp dateTime) throws UserNotFoundException, TaskNotFoundException {
        Attendance attendance = new Attendance();
        User user = userRepository.findUserById(userId);
        Task task = taskRepository.findTaskById(taskId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        if (task == null) {
            throw new TaskNotFoundException();
        }
        attendance.setUser(user);
        attendance.setTask(task);
        attendance.setBeginTime(dateTime);
        attendanceRepository.save(attendance);
        return user;
    }

    @Override
    public User signout(long userId, long taskId, Timestamp dateTime) throws UserNotFoundException, TaskNotFoundException, AttendanceNotFoundException {
        User user = userRepository.findUserById(userId);
        Task task = taskRepository.findTaskById(taskId);
        Attendance attendance = attendanceRepository.findAttendanceByUserAndTask(user, task);
        if (user == null) {
            throw new UserNotFoundException();
        }
        if (task == null) {
            throw new TaskNotFoundException();
        }
        if (attendance == null) {
            throw new AttendanceNotFoundException();
        }
        attendance.setEndTime(dateTime);
        attendanceRepository.save(attendance);
        return user;
    }

    /**
     * 获取人脸信息
     * @return
     */
    @Override
    public String getUserFaceUrlById(long userId) throws UserNotFoundException {
        User userRecorded = userRepository.findUserById(userId);
        if (userRecorded == null) {
            throw new UserNotFoundException();
        }
        return userRecorded.getFaceUrl();
    }
}
