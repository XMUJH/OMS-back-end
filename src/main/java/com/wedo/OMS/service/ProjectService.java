package com.wedo.OMS.service;

import com.wedo.OMS.entity.Project;

public interface ProjectService {
    /**
     * 新增项目
     * @param project
     * @param belong 所属项目的ID
     * @return
     */
    void addProject(Project project, Long belong);

    /**
     * 将项目移入另一个项目中
     * @param InprojectId
     * @param OutprojectId
     * @return
     */
    void MoveInprojectToOutprojectByProjectId(Long InprojectId,Long OutprojectId);

    /**
     * 将任务移入项目中
     * @param taskId
     * @param projectId
     * @return
     */
    void MoveTaskToProjectById(Long taskId,Long projectId);

    /**
     * 计算进度
     * @param projectId
     * @return 百分数
     */
    long CalScheduleByProjectId(Long projectId);

    /**
     * 删除项目
     * @param projectId
     */
    void deleteProjectByProjectId(Long projectId);
}
