package com.wedo.OMS.service;

import com.wedo.OMS.entity.Project;

public interface ProjectService {
    /**
     * 新增项目
     * @param project
     * @param belong 所属项目的ID
     * @return
     */
    void addProject(Project project, long belong);

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
    void MoveTaskToProjectById(long taskId,long projectId);

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
}
