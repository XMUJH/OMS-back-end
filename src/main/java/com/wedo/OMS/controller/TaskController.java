package com.wedo.OMS.controller;

import com.wedo.OMS.entity.Code;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.entity.UserTask;
import com.wedo.OMS.enums.UserTaskRole;
import com.wedo.OMS.service.ProjectService;
import com.wedo.OMS.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private TaskService taskService;
    private ProjectService projectService;
    public TaskController(TaskService taskService,ProjectService projectService){
        this.taskService = taskService;
        this.projectService = projectService;
    }

    /**
     * 用户获取我负责的任务/我参与的任务
     * @param userTaskRole
     * @param userId
     * @return
     */
    @GetMapping(value = "/users/:id/tasks")
    public List<Task> getTask(@PathVariable("userId") long userId,@RequestBody UserTaskRole userTaskRole){
        return taskService.findTasks(userId,userTaskRole);
    }

    /**
     * 用户新建任务
     * @param task
     * @param projectId 任务所属项目id
     * @return
     */
    @PostMapping(value = "/tasks")
    public Code addTask(@RequestBody Task task,@RequestBody long projectId){
        return taskService.addTask(task,projectId);
    }

    /**
     * 用户通过拖曳的方式将任务放进一个项目里
     * @param taskId 任务id
     * @param projectId 目标项目id
     */
    @PatchMapping(value = "/tasks/:id")
    public Task MoveTaskToProjectById(@PathVariable("taskId") long taskId,@RequestBody long projectId){
        return projectService.MoveTaskToProjectById(taskId,projectId);
    }

    /**
     * 用户获取单个任务
     * @param taskId
     * @return
     */
    @GetMapping(value = "/tasks/:id")
    public Task getTaskByTaskId(@PathVariable("taskId") long taskId){
        return taskService.getTaskByTaskId(taskId);
    }

    /**
     * 负责人激活任务（更新code状态，添加一条userTask信息）
     * @param userId
     * @param code
     * @return
     */
    @PatchMapping(value = "/users/:id/codes/:code")
    public UserTask addUserTask(@PathVariable("userId")long userId,@PathVariable("code") String code){
        Code code1 = taskService.findCodeByCode(code);
        if(code1!=null) {
            code1 = taskService.updateCodeStatus(code1);
            return taskService.addUserTask(code1,userId);
        }
        else
            return null;
    }


}
