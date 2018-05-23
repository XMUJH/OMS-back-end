package com.wedo.OMS.repository;


import com.wedo.OMS.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
    Record findRecordById(long recordId);
}
