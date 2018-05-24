package com.wedo.OMS.controller;

import com.wedo.OMS.entity.Attendance;
import com.wedo.OMS.entity.Record;
import com.wedo.OMS.entity.User;
import com.wedo.OMS.service.AttendanceService;
import com.wedo.OMS.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class AttendanceController {
    private AttendanceService attendanceService;
    private UserService userService;
    public AttendanceController(AttendanceService attendanceService,UserService userService){
        this.attendanceService = attendanceService;
        this.userService= userService;
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

    /**
     * 获取考勤日志
     * @param attendanceId
     * @return
     */
    @GetMapping(value = "/attendances/:attendanceId")
    public Record getRecordByAttendanceId(@PathVariable("attendanceId")long attendanceId){
        return attendanceService.getRecordByAttendanceId(attendanceId);
    }

    /**
     * 签到
     * @param userId
     * @param taskId
     * @param beginTime
     * @return
     */
    @PostMapping(value = "/attendances")
    public User signIn(@RequestBody long userId, @RequestBody long taskId, @RequestBody Timestamp beginTime){
        return userService.signin(userId, taskId, beginTime);
    }

    /**
     * 签退
     * @param userId
     * @param taskId
     * @param endTime
     * @return
     */
    @PatchMapping(value = "/attendances")
    public User signOut(@RequestBody long userId, @RequestBody long taskId, @RequestBody Timestamp endTime){
        return userService.signout(userId, taskId, endTime);
    }

}
