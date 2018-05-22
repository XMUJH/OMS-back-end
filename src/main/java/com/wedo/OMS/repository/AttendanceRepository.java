package com.wedo.OMS.repository;

import com.wedo.OMS.entity.Attendance;
import com.wedo.OMS.entity.Record;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findAttendancesByUser(User user);
    List<Attendance> findAttendancesByTask(Task task);
    Attendance findAttendanceByUserAndTask(User user,Task task);
}
