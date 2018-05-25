package com.wedo.OMS.controller;

import com.wedo.OMS.entity.Attendance;
import com.wedo.OMS.entity.Record;
import com.wedo.OMS.entity.User;
import com.wedo.OMS.exception.AttendanceNotFoundException;
import com.wedo.OMS.exception.RecordNotFoundException;
import com.wedo.OMS.exception.TaskNotFoundException;
import com.wedo.OMS.exception.UserNotFoundException;
import com.wedo.OMS.service.AttendanceService;
import com.wedo.OMS.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.wedo.OMS.vo.UserAttendance;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class AttendanceController {
    private final AttendanceService attendanceService;
    private final UserService userService;

    public AttendanceController(AttendanceService attendanceService, UserService userService) {
        this.attendanceService = attendanceService;
        this.userService = userService;
    }

    @GetMapping(value = "/users/{userId}/attendances")
    public List<Attendance> getAttendancesByUserId(@PathVariable("userId") long userId) throws UserNotFoundException {
        return attendanceService.getAttendancesByUserId(userId);
    }

    @GetMapping(value = "/tasks/{taskId}/attendances")
    public List<Attendance> getTaskAttendancesByTaskId(@PathVariable("taskId") long taskId) throws TaskNotFoundException {
        return attendanceService.getTaskAttendancesByTaskId(taskId);
    }

    @GetMapping(value = "/attendances/{attendanceId}")
    public Record getRecordByAttendanceId(@PathVariable("attendanceId") long attendanceId) throws RecordNotFoundException, AttendanceNotFoundException {
        return attendanceService.getRecordByAttendanceId(attendanceId);
    }

    @PostMapping(value = "/attendances")
    public User signIn(@RequestBody UserAttendance userAttendance) throws UserNotFoundException, TaskNotFoundException {
        Timestamp startTimeStamp = new Timestamp( userAttendance.getDate().getTime());
        return userService.signin(userAttendance.getUserId(), userAttendance.getTaskId(), startTimeStamp);
    }

    @PatchMapping(value = "/attendances")
    public User signOut(@RequestBody UserAttendance userAttendance) throws UserNotFoundException, AttendanceNotFoundException, TaskNotFoundException {
        Timestamp endTimeStamp = new Timestamp( userAttendance.getDate().getTime());
        return userService.signout(userAttendance.getUserId(), userAttendance.getTaskId(), endTimeStamp);
    }

}
