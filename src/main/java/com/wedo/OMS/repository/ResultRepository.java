package com.wedo.OMS.repository;

import com.wedo.OMS.entity.Milestone;
import com.wedo.OMS.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findAllByMilestone(Milestone milestone);
    List<Result> findResultsByMilestone(Milestone milestone);
    Result findResultById(long resultId);
}