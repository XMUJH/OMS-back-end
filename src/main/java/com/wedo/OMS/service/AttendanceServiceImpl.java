package com.wedo.OMS.service;

import com.wedo.OMS.entity.Attendance;
import com.wedo.OMS.entity.Record;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.entity.User;
import com.wedo.OMS.exception.AttendanceNotFoundException;
import com.wedo.OMS.exception.RecordNotFoundException;
import com.wedo.OMS.exception.TaskNotFoundException;
import com.wedo.OMS.exception.UserNotFoundException;
import com.wedo.OMS.repository.AttendanceRepository;
import com.wedo.OMS.repository.RecordRepository;
import com.wedo.OMS.repository.TaskRepository;
import com.wedo.OMS.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final RecordRepository recordRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, RecordRepository recordRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.attendanceRepository = attendanceRepository;
        this.recordRepository = recordRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    /**
     * 用户获取个人考勤情况
     *
     * @param userId
     * @return
     */
    @Override
    public List<Attendance> getAttendancesByUserId(long userId) throws UserNotFoundException {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return attendanceRepository.findAttendancesByUser(user);
    }

    /**
     * 获取每次考勤的考勤日志
     *
     * @return
     */
    @Override
    public Record getRecordByAttendanceId(long attendanceId) throws AttendanceNotFoundException, RecordNotFoundException {
        Attendance attendance = attendanceRepository.findAttendanceById(attendanceId);
        if (attendance == null) {
            throw new AttendanceNotFoundException();
        }
        Record record = recordRepository.findRecordByAttendance(attendance);
        if (record == null) {
            throw new RecordNotFoundException();
        }
        return record;
    }

    /**
     * 队长或发包方根据任务ID获取任务考勤情况
     *
     * @param taskId
     * @return
     */
    @Override
    public List<Attendance> getTaskAttendancesByTaskId(long taskId) throws TaskNotFoundException {
        Task task = taskRepository.findTaskById(taskId);
        if (task == null) {
            throw new TaskNotFoundException();
        }
        return attendanceRepository.findAttendancesByTask(task);
    }
}
