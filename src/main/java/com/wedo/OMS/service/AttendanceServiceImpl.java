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
    @Override
    public List<Attendance> getAttendancesByUserId(Long userId) {
        User user = userRepository.findUserById(userId);
        return attendanceRepository.findAttendancesByUser(user);
    }

    @Override
    public List<Attendance> getTaskAttendancesByTaskId(Long taskId) {
        Task task = taskRepository.findTaskById(taskId);
        return attendanceRepository.findAttendancesByTask(task);
    }

    @Override
    public Record getAttendanceRecordByRecordId(Long recordId) {
        return recordRepository.findRecordById(recordId);
    }
}
