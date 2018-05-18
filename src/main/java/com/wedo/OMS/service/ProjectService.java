package com.wedo.OMS.service;

import com.wedo.OMS.entity.Project;

public interface ProjectService {
    /**
     * 新增项目
     *
     * @param project
     * @param belong  所属项目的ID
     * @return
     */
    Project addProject(Project project, Long belong);

    /**
     * 更新项目
     *
     * @param projectId
     * @return
     */
    Project updateProjectByProjectId(Long projectId);

    /**
     * 删除项目
     *
     * @param projectId
     */
    void deleteProjectByProjectId(Long projectId);
}
