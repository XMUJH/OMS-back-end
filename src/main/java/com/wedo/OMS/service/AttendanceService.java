package com.wedo.OMS.service;

import com.wedo.OMS.entity.Attendance;
import com.wedo.OMS.entity.Record;

import java.util.List;

public interface AttendanceService {
    /**
     * 用户获取个人考勤情况
     * @param userId
     * @return
     */
    List<Attendance> getAttendancesByUserId(long userId);

    /**
     * 队长或发包方根据任务ID获取任务考勤情况
     * @param taskId
     * @return
     */
    List<Attendance> getTaskAttendancesByTaskId(long taskId);

    /**
     * 查看考勤日志
     * @param recordId
     * @return
     */
    Record getAttendanceRecordByRecordId(long recordId);

}
