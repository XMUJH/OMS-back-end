package com.wedo.OMS.controller;

import com.wedo.OMS.entity.Attendance;
import com.wedo.OMS.service.AttendanceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AttendanceController {
    private AttendanceService attendanceService;
    public AttendanceController(AttendanceService attendanceService){
        this.attendanceService = attendanceService;
    }

    /**
     * 用户获取个人考勤情况
     * @param userId
     * @return
     */
    @GetMapping(value = "/users/:userId/attendances")
    public List<Attendance> getAttendancesByUserId(@PathVariable("userId") long userId){
        return attendanceService.getAttendancesByUserId(userId);
    }

    /**
     * 管理员获取该项目所有人员所有考勤记录
     * @param taskId
     * @return
     */
    @GetMapping(value = "/tasks/:taskId/attendances")
    public List<Attendance> getTaskAttendancesByTaskId(@PathVariable("taskId")long taskId){
        return attendanceService.getTaskAttendancesByTaskId(taskId);
    }
}
