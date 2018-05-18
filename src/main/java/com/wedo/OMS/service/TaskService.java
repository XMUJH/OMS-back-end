package com.wedo.OMS.service;

import com.wedo.OMS.entity.Task;
import com.wedo.OMS.entity.UserTask;
import com.wedo.OMS.enums.UserTaskRole;
import com.wedo.OMS.enums.VerifyStatus;

import java.util.List;

public interface TaskService {
    /**
     * 根据用户ID获取任务列表
     *
     * @param userId
     * @return
     */
    List<Task> listTasksByUserId(Long userId);

    /**
     * 根据任务ID获取任务
     *
     * @param taskId
     * @return
     */
    Task getTaskByTaskId(Long taskId);

    /**
     * 根据任务名称搜索任务
     *
     * @param taskname
     * @return
     */
    List<Task> getTasksByTaskname(String taskname);

    /**
     * 发包方新建任务，包括上传合同信息，保密协议和生成任务授权码
     *
     * @param task
     * @param belong 新增任务所属项目Id
     * @return
     */
    Task addTask(Task task, Long belong);

    /**
     * 根据任务ID修改任务，如修改任务合同信息或保密协议
     *
     * @param taskId
     * @param task
     * @return
     */
    Task updateTaskByTaskId(Long taskId, Task task);

    /**
     * 队长新增任务成员
     *
     * @param userId
     * @param utr
     * @return
     */
    UserTask addTaskUser(Long userId, UserTaskRole utr);

    /**
     * 发包方审核任务成员
     *
     * @param userId
     * @param taskId
     * @param status
     * @return
     */
    UserTask auditTaskUserById(Long userId, Long taskId, VerifyStatus status);

    /**
     * 删除任务成员
     *
     * @param userId
     * @param tastId
     */
    void deleteTaskUserById(Long userId, Long tastId);
}
