package com.wedo.OMS.service;

import com.wedo.OMS.entity.Code;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.entity.User;
import com.wedo.OMS.entity.UserTask;
import com.wedo.OMS.enums.UserTaskRole;
import com.wedo.OMS.enums.VerifyStatus;

import java.util.List;

public interface TaskService {
    /**
     * 根据用户ID获取任务列表
     * @param userId
     * @return
     */
    List<Task> listTasksByUserId(long userId);

    /**
     * 根据任务ID获取任务
     * @param taskId
     * @return
     */
    Task getTaskByTaskId(long taskId);

    /**
     * 根据任务名称搜索任务
     * @param taskname
     * @return
     */
    List<Task> getTasksByTaskname(String taskname);

    /**
     * 发包方新建任务，包括上传合同信息，保密协议和生成任务授权码
     *
     * @param task
     */
    Code addTask(Task task,long projectId);

    /**
     * 根据任务ID修改任务，如修改任务合同信息或保密协议
     * @param taskId
     * @param task
     * @return
     */
    Task updateTaskByTaskId(long taskId, Task task);

    /**
     * 队长新增任务成员
     * @param userId
     * @param utr
     */
    UserTask addTaskUser(long taskId,long userId, UserTaskRole utr);

    /**
     * 发包方审核任务成员
     * @param userId
     * @param taskId
     * @param status
     * @return
     */
    UserTask auditTaskUserById(long userId, long taskId, VerifyStatus status);

    /**
     * 删除任务成员
     * @param userId
     * @param taskId
     */
    void deleteTaskUserById(long userId, long taskId);


    /**
     * 根据人员身份查找该身份全部任务
     * @param userId
     * @param userTaskRole
     * @return
     */
    List<Task> findTasks(long userId,UserTaskRole userTaskRole);

    /**
     * 根据激活码找到该code
     * @param code
     * @return
     */
    Code findCodeByCode(String code);

    /**
     * 任务激活后更改任务状态
     * @return
     */
    Code updateCodeStatus(Code code);

    /**
     * 任务激活后添加userTask负责人信息
     * @param code
     * @param userId
     * @return
     */
    UserTask addUserTask(Code code,long userId);

    /**
     * 查询该任务所有成员
     * @param taskId
     * @return
     */
    List<User> findUsersByTaskId(long taskId);
}
