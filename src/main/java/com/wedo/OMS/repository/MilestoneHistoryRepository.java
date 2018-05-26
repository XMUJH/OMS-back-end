package com.wedo.OMS.repository;

import com.wedo.OMS.entity.Milestone;
import com.wedo.OMS.entity.MilestoneHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MilestoneHistoryRepository extends JpaRepository<MilestoneHistory, Long> {
    List<MilestoneHistory> findMilestoneHistoriesByMilestone(Milestone milestone);
    MilestoneHistory findMilestoneHistoryById(long id);
    MilestoneHistory findMilestoneHistoryByStatusAndMilestone(long status,Milestone milestone);
}
