package com.wedo.OMS.repository;

import com.wedo.OMS.entity.Milestone;
import com.wedo.OMS.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

    List<Milestone> findMilestonesByTask(Task task);

    Milestone findMilestoneById(long milestoneId);

}
