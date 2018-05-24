package com.wedo.OMS.repository;


import com.wedo.OMS.entity.Attendance;
import com.wedo.OMS.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    Record findRecordById(long recordId);
    Record findRecordByAttendance(Attendance attendance);
}
