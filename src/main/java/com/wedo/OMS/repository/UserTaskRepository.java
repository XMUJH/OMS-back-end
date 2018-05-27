package com.wedo.OMS.repository;

import com.wedo.OMS.entity.Task;
import com.wedo.OMS.entity.User;
import com.wedo.OMS.entity.UserTask;
import com.wedo.OMS.enums.UserTaskRole;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface UserTaskRepository extends JpaRepository<UserTask, Long> {
    List<UserTask> findUserTasksByUser(User user);
    List<UserTask> findUserTasksByUserAndUserTaskRole(User user, UserTaskRole userTaskRole);
    @Transactional
    void deleteByUserAndTask(User user, Task task);
    UserTask findUserTasksByUserAndTask(User user, Task task);
    List<UserTask> findUserTasksByTask(Task task);
}
