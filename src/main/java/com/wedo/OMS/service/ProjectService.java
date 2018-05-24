package com.wedo.OMS.service;

import com.wedo.OMS.entity.Project;
import com.wedo.OMS.entity.Task;

public interface ProjectService {
    /**
     * 新增项目
     * @param project
     * @param belong 所属项目的ID
     * @return
     */
    Project addProject(Project project, long belong);

    /**
     * 将项目移入另一个项目中
     * @param InprojectId
     * @param OutprojectId
     * @return
     */
    void MoveInprojectToOutprojectByProjectId(long InprojectId,long OutprojectId);

    /**
     * 将任务移入项目中
     * @param taskId
     * @param projectId
     * @return
     */
    Task MoveTaskToProjectById(long taskId, long projectId);

    /**
     * 计算进度
     * @param projectId
     * @return 百分数
     */
    long CalScheduleByProjectId(long projectId);

    /**
     * 删除项目
     * @param projectId
     */
    void deleteProjectByProjectId(long projectId);

    /**
     * 获取项目信息
     * @param projectId
     * @return
     */
    Project getProjectsByProjectId(long projectId);
}
