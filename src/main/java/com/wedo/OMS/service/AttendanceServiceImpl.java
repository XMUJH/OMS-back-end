package com.wedo.OMS.service;

import com.wedo.OMS.entity.Attendance;
import com.wedo.OMS.entity.Record;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Override
    public List<Attendance> getAttendancesByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Attendance> getTaskAttendancesByTaskId(Long taskId) {
        return null;
    }

    @Override
    public Record getAttendanceRecordByRecordId(Long recordId) {
        return null;
    }
}
