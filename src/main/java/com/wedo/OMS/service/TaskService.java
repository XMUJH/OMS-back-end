package com.wedo.OMS.service;

import com.wedo.OMS.entity.*;
import com.wedo.OMS.enums.UserTaskRole;
import com.wedo.OMS.enums.VerifyStatus;
import com.wedo.OMS.exception.*;

import java.util.List;

public interface TaskService {
    /**
     * 根据用户ID获取任务列表
     *
     * @param userId 用户ID
     * @return 用户实体类
     */
    List<Task> listTasksByUserId(long userId) throws UserNotFoundException;

    /**
     * 根据任务ID获取任务
     *
     * @param taskId 任务ID
     * @return 任务实体
     */
    Task getTaskByTaskId(long taskId) throws TaskNotFoundException;

    /**
     * 根据任务名称搜索任务
     *
     * @param taskName 任务名称
     * @return 包含该名称的任务列表
     */
    List<Task> getTasksByTaskName(String taskName);

    /**
     * 发包方新建任务，包括上传合同信息，保密协议和生成任务授权码
     *
     * @param task      任务实体类
     * @param projectId 项目ID
     */
    Code addTask(Task task, long projectId) throws ProjectNotFoundException;

    /**
     * 根据任务ID修改任务，如修改任务合同信息或保密协议
     *
     * @param taskId 原始任务ID
     * @param task   更新的任务信息
     * @return 任务实体类
     */
    //TODO Should be replaced by true or false
    Task updateTaskByTaskId(long taskId, Task task);

    /**
     * 队长新增任务成员
     *
     * @param userId       用户ID
     *
     */
    UserTask addTaskUser(long taskId, long userId, String job) throws UserNotFoundException, TaskNotFoundException;

    /**
     * 发包方审核任务成员
     *
     * @param userId 用户id
     * @param taskId 任务id
     * @param status 审核状态
     * @return 用户任务实体类
     */
    UserTask auditTaskUserById(long userId, long taskId, VerifyStatus status) throws UserNotFoundException, TaskNotFoundException;

    /**
     * 删除任务成员
     *
     * @param userId 被删除的用户id
     * @param taskId 任务id
     */
    void deleteTaskUserById(long userId, long taskId) throws UserNotFoundException, TaskNotFoundException;


    /**
     * 根据人员身份查找该身份全部任务
     *
     * @param userId       用户id
     * @param userTaskRole 用户角色
     * @return 任务列表
     */
    List<Task> findTasks(long userId, UserTaskRole userTaskRole) throws UserNotFoundException;

    /**
     * 根据激活码找到该code
     *
     * @param code 原始激活码
     * @return 对应激活码实体类
     * @throws CodeNotFoundException 无对应激活码
     */
    Code findCodeByCode(String code) throws CodeNotFoundException;

    /**
     * 任务激活后更改激活码状态
     *
     * @return 激活码
     */
    //TODO Should be replaced by true or false
    Code updateCodeStatus(Code code);

    /**
     * 任务激活后添加userTask负责人信息
     *
     * @param code   激活码
     * @param userId 用户id
     * @return 用户任务
     */
    UserTask addUserTask(Code code, long userId) throws UserNotFoundException, TaskNotFoundException;

    /**
     * 查询该任务所有成员
     *
     * @param taskId 任务id
     * @return 成员列表
     */
    List<UserTask> findUsersByTaskId(long taskId) throws TaskNotFoundException;

    /**
     * 列出所有日志
     */
    List<Record> findAllRecord() throws RecordNotFoundException;
}
