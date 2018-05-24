package com.wedo.OMS.service;

import com.wedo.OMS.entity.Attendance;
import com.wedo.OMS.entity.Record;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.entity.User;
import com.wedo.OMS.repository.TaskRepository;
import com.wedo.OMS.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.wedo.OMS.repository.AttendanceRepository;
import com.wedo.OMS.repository.RecordRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    private AttendanceRepository attendanceRepository;
    private RecordRepository recordRepository;
    private UserRepository userRepository;
    private TaskRepository taskRepository;
    public AttendanceServiceImpl(AttendanceRepository attendanceRepository,RecordRepository recordRepository,UserRepository userRepository,TaskRepository taskRepository){
        this.attendanceRepository =attendanceRepository;
        this.recordRepository=recordRepository;
        this.userRepository=userRepository;
        this.taskRepository=taskRepository;
    }
    /**
     * 用户获取个人考勤情况
     * @param userId
     * @return
     */
    @Override
    public List<Attendance> getAttendancesByUserId(long userId) {
        User user = userRepository.findUserById(userId);
        return attendanceRepository.findAttendancesByUser(user);
    }

    /**
     * 获取每次考勤的考勤日志
     * @return
     */
    @Override
    public Record getRecordByAttendanceId(long attendanceId){
        Attendance attendance= attendanceRepository.findAttendanceById(attendanceId);
        return recordRepository.findRecordByAttendance(attendance);
    }

    /**
     * 队长或发包方根据任务ID获取任务考勤情况
     * @param taskId
     * @return
     */
    @Override
    public List<Attendance> getTaskAttendancesByTaskId(long taskId) {
        Task task = taskRepository.findTaskById(taskId);
        return attendanceRepository.findAttendancesByTask(task);
    }

//    /**
//     * 查看考勤日志
//     * @param recordId
//     * @return
//     */
//    @Override
//    public Record getAttendanceRecordByRecordId(long recordId) {
//        return recordRepository.findRecordById(recordId);
//    }
}
