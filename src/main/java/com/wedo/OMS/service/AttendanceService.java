package com.wedo.OMS.service;

import com.wedo.OMS.entity.Attendance;
import com.wedo.OMS.entity.Record;
import com.wedo.OMS.exception.AttendanceNotFoundException;
import com.wedo.OMS.exception.RecordNotFoundException;
import com.wedo.OMS.exception.TaskNotFoundException;
import com.wedo.OMS.exception.UserNotFoundException;

import java.util.List;

public interface AttendanceService {
    /**
     * 用户获取个人考勤情况
     * @param userId
     * @return
     */
    List<Attendance> getAttendancesByUserId(long userId) throws UserNotFoundException;

    /**
     * 获取每次考勤的考勤日志
     * @return
     */
    Record getRecordByAttendanceId(long attendanceId) throws AttendanceNotFoundException, RecordNotFoundException;

    /**
     * 队长或发包方根据任务ID获取任务考勤情况
     * @param taskId
     * @return
     */
    List<Attendance> getTaskAttendancesByTaskId(long taskId) throws TaskNotFoundException;

//    /**
//     * 查看考勤日志
//     * @param recordId
//     * @return
//     */
//    Record getAttendanceRecordByRecordId(long recordId);

}
