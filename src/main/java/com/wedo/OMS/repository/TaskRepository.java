package com.wedo.OMS.repository;

import com.wedo.OMS.entity.Project;
import com.wedo.OMS.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findTaskById(long taskId);
    List<Task> findTasksByNameContaining(String name);
    Task findTaskByName(String name);
    List<Task> findTasksByProject(Project project);
}
