package com.wedo.OMS.controller;

import com.wedo.OMS.entity.*;
import com.wedo.OMS.enums.SafetyLevel;
import com.wedo.OMS.enums.UserTaskRole;
import com.wedo.OMS.enums.VerifyStatus;
import com.wedo.OMS.exception.CodeNotFoundException;
import com.wedo.OMS.exception.ProjectNotFoundException;
import com.wedo.OMS.exception.TaskNotFoundException;
import com.wedo.OMS.exception.UserNotFoundException;
import com.wedo.OMS.service.ProjectService;
import com.wedo.OMS.service.TaskService;
import com.wedo.OMS.vo.EnumChoice;
import com.wedo.OMS.vo.NewTask;
import com.wedo.OMS.vo.OneLong;
import com.wedo.OMS.vo.OneString;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
     * @param utr
     * @param userId
     * @return
     */
    @PostMapping(value = "/users/{userId}/tasks")
    public List<Task> getTask(@PathVariable("userId") long userId, @RequestBody EnumChoice utr) throws UserNotFoundException {
        List<Task> tasks = new ArrayList<>();
        switch (utr.getChoice()){
            case "LEADER":
                tasks = taskService.findTasks(userId, UserTaskRole.LEADER);
                break;
            case "FOLLOWER":
                tasks = taskService.findTasks(userId, UserTaskRole.FOLLOWER);
                break;
        }
        return tasks;
    }

    /**
     * 用户新建任务
     *
     * @param newTask 任务信息及任务所属项目id
     * @return
     */
    @PostMapping(value = "/tasks")
    public Code addTask(@RequestBody NewTask newTask) throws ProjectNotFoundException {
        Task task = new Task();
        task.setProject(projectService.findProjectByProjectId(newTask.getProjectId()));
        task.setCompletion(newTask.getCompletion());
        task.setInfo(newTask.getInfo());
        task.setName(newTask.getName());
        task.setAgreementUrl(newTask.getAgreementUrl());
        task.setContractUrl(newTask.getContractUrl());
        task.setBeginTime(newTask.getBeginTime());
        task.setChangeCount(newTask.getChangeCount());
        task.setCreateTime(newTask.getCreateTime());
        task.setEndTime(newTask.getEndTime());
        switch(newTask.getSafety()){
            case("A"):
                task.setSafety(SafetyLevel.A);
                break;
            case("B"):
                task.setSafety(SafetyLevel.B);
                break;
            case("C"):
                task.setSafety(SafetyLevel.C);
                break;
            case("D"):
                task.setSafety(SafetyLevel.D);
                break;
            case("E"):
                task.setSafety(SafetyLevel.E);
                break;
        }
        return taskService.addTask(task, newTask.getProjectId());
    }

    /**
     * 用户通过拖曳的方式将任务放进一个项目里
     *
     * @param taskId    任务id
     * @param projectId 目标项目id
     */
    @PatchMapping(value = "/tasks/{taskId}")
    public Task MoveTaskToProjectById(@PathVariable("taskId") long taskId, @RequestBody OneLong projectId) throws ProjectNotFoundException, TaskNotFoundException {
        long id = projectId.getId();
        return projectService.MoveTaskToProjectById(taskId, id);
    }

    /**
     * 用户获取单个任务
     *
     * @param taskId
     * @return
     */
    @GetMapping(value = "/tasks/{taskId}")
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
    @PatchMapping(value = "/users/{userId}/codes/{code}")
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
    @GetMapping(value = "/tasks/{taskId}/users")
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
    @PostMapping(value = "/tasks/{taskId}/users/{userId}")
    public UserTask addTaskUser(@PathVariable("taskId") long taskId, @PathVariable("userId") long userId, @RequestBody EnumChoice utr) throws UserNotFoundException, TaskNotFoundException {
        UserTask userTask = new UserTask();
        switch (utr.getChoice()){
            case "LEADER":
                userTask = taskService.addTaskUser(taskId, userId, UserTaskRole.LEADER);
                break;
            case "FOLLOWER":
                userTask = taskService.addTaskUser(taskId, userId, UserTaskRole.FOLLOWER);
                break;
        }
        return userTask;
    }

    /**
     * 审核任务成员
     *
     * @param userId
     * @param taskId
     * @param verifyStatus
     * @return
     */
    @PatchMapping(value = "/tasks/{taskId}/users/{userId}")
    public UserTask auditTaskUserById(@PathVariable("userId") long userId, @PathVariable("taskId") long taskId, @RequestBody EnumChoice verifyStatus) throws UserNotFoundException, TaskNotFoundException {
        UserTask userTask = new UserTask();
        switch (verifyStatus.getChoice()){
            case "NORMAL":
                userTask = taskService.auditTaskUserById(userId, taskId, VerifyStatus.NORMAL);
                break;
            case "ADD_CHECK":
                userTask = taskService.auditTaskUserById(userId, taskId, VerifyStatus.ADD_CHECK);
                break;
            case "DELETE_CHECK":
                userTask = taskService.auditTaskUserById(userId, taskId, VerifyStatus.DELETE_CHECK);
                break;
        }
        return userTask;
    }
}
