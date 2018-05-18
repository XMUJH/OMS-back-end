package com.wedo.OMS.service;

import com.wedo.OMS.entity.Task;
import com.wedo.OMS.entity.UserTask;
import com.wedo.OMS.enums.UserTaskRole;
import com.wedo.OMS.enums.VerifyStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Override
    public List<Task> listTasksByUserId(Long userId) {
        return null;
    }

    @Override
    public Task getTaskByTaskId(Long taskId) {
        return null;
    }

    @Override
    public List<Task> getTasksByTaskname(String taskname) {
        return null;
    }

    @Override
    public Task addTask(Task task, Long belong) {
        return null;
    }

    @Override
    public Task updateTaskByTaskId(Long taskId, Task task) {
        return null;
    }

    @Override
    public UserTask addTaskUser(Long userId, UserTaskRole utr) {
        return null;
    }

    @Override
    public UserTask auditTaskUserById(Long userId, Long taskId, VerifyStatus status) {
        return null;
    }

    @Override
    public void deleteTaskUserById(Long userId, Long tastId) {

    }
}
