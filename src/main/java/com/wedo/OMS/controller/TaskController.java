package com.wedo.OMS.controller;

import com.wedo.OMS.entity.Code;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.entity.User;
import com.wedo.OMS.entity.UserTask;
import com.wedo.OMS.enums.UserTaskRole;
import com.wedo.OMS.enums.VerifyStatus;
import com.wedo.OMS.exception.CodeNotFoundException;
import com.wedo.OMS.exception.ProjectNotFoundException;
import com.wedo.OMS.exception.TaskNotFoundException;
import com.wedo.OMS.exception.UserNotFoundException;
import com.wedo.OMS.service.ProjectService;
import com.wedo.OMS.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO fill in javadoc or remove it.
@RestController
public class TaskController {
    private final TaskService taskService;
    private final ProjectService projectService;

    public TaskController(TaskService taskService, ProjectService projectService) {
        this.taskService = taskService;
        this.projectService = projectService;
    }

    /**
     * 用户获取我负责的任务/我参与的任务
     *
     * @param userTaskRole
     * @param userId
     * @return
     */
    @GetMapping(value = "/users/:userId/tasks")
    public List<Task> getTask(@PathVariable("userId") long userId, @RequestBody UserTaskRole userTaskRole) throws UserNotFoundException {
        return taskService.findTasks(userId, userTaskRole);
    }

    /**
     * 用户新建任务
     *
     * @param task
     * @param projectId 任务所属项目id
     * @return
     */
    @PostMapping(value = "/tasks")
    public Code addTask(@RequestBody Task task, @RequestBody long projectId) throws ProjectNotFoundException {
        return taskService.addTask(task, projectId);
    }

    /**
     * 用户通过拖曳的方式将任务放进一个项目里
     *
     * @param taskId    任务id
     * @param projectId 目标项目id
     */
    @PatchMapping(value = "/tasks/:taskId")
    public Task MoveTaskToProjectById(@PathVariable("taskId") long taskId, @RequestBody long projectId) throws ProjectNotFoundException, TaskNotFoundException {
        return projectService.MoveTaskToProjectById(taskId, projectId);
    }

    /**
     * 用户获取单个任务
     *
     * @param taskId
     * @return
     */
    @GetMapping(value = "/tasks/:taskId")
    public Task getTaskByTaskId(@PathVariable("taskId") long taskId) throws TaskNotFoundException {
        return taskService.getTaskByTaskId(taskId);
    }

    /**
     * 负责人激活任务（更新code状态，添加一条userTask信息）
     *
     * @param userId
     * @param origin
     * @return
     */
    @PatchMapping(value = "/users/:userId/codes/:code")
    public UserTask addUserTask(@PathVariable("userId") long userId, @PathVariable("code") String origin) throws CodeNotFoundException, UserNotFoundException, TaskNotFoundException {
        Code code = taskService.findCodeByCode(origin);
        if (code != null) {
            code = taskService.updateCodeStatus(code);
            return taskService.addUserTask(code, userId);
        } else
            return null;
    }

    /**
     * 该任务所有成员
     *
     * @param taskId
     * @return
     */
    @GetMapping(value = "/tasks/:taskId/users")
    public List<User> findUsersByTaskId(@PathVariable("taskId") long taskId) throws TaskNotFoundException {
        return taskService.findUsersByTaskId(taskId);
    }

    /**
     * 队长添加成员
     *
     * @param taskId
     * @param userId
     * @param utr
     * @return
     */
    @PostMapping(value = "/tasks/:taskId/users/:userId")
    public UserTask addTaskUser(@PathVariable("taskId") long taskId, @PathVariable("userId") long userId, @RequestBody UserTaskRole utr) throws UserNotFoundException, TaskNotFoundException {
        return taskService.addTaskUser(taskId, userId, utr);
    }

    /**
     * 审核任务成员
     *
     * @param userId
     * @param taskId
     * @param status
     * @return
     */
    @PatchMapping(value = "/tasks/:taskId/users/:userId")
    public UserTask auditTaskUserById(@PathVariable("userId") long userId, @PathVariable("taskId") long taskId, @RequestBody VerifyStatus status) throws UserNotFoundException, TaskNotFoundException {
        return taskService.auditTaskUserById(userId, taskId, status);
    }
}
