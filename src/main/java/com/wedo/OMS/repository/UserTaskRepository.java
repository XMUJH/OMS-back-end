package com.wedo.OMS.repository;

import com.wedo.OMS.entity.Task;
import com.wedo.OMS.entity.User;
import com.wedo.OMS.entity.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTaskRepository extends JpaRepository<UserTask, Long> {
    List<UserTask> findUserTasksByUser(User user);

    void deleteByUserAndTask(User user, Task task);

    UserTask findUserTasksByUserAndTask(User user, Task task);
}
