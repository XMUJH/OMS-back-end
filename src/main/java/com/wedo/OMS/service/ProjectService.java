package com.wedo.OMS.service;
import com.wedo.OMS.entity.Project;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.exception.ProjectNotFoundException;
import com.wedo.OMS.exception.TaskNotFoundException;
import java.util.List;

public interface ProjectService {
    /**
     * 新增项目
     *
     * @param project
     * @param belong  所属项目的ID
     * @return
     */
    Project addProject(Project project, long belong) throws ProjectNotFoundException;

    /**
     * 将项目移入另一个项目中
     *
     * @param InprojectId
     * @param OutprojectId
     * @return
     */
    void MoveInProjectToOutProjectByProjectId(long InprojectId, long OutprojectId) throws ProjectNotFoundException;

    /**
     * 将任务移入项目中
     *
     * @param taskId
     * @param projectId
     * @return
     */
    Task MoveTaskToProjectById(long taskId, long projectId) throws TaskNotFoundException, ProjectNotFoundException;

    /**
     * 计算进度
     *
     * @param projectId
     * @return 百分数
     */
    long CalScheduleByProjectId(long projectId) throws ProjectNotFoundException;

    /**
     * 删除项目
     *
     * @param projectId
     */
    void deleteProjectByProjectId(long projectId);

    /**
     * 获取项目信息
     *
     * @param projectId
     * @return
     */
    Project getProjectsByProjectId(long projectId) throws ProjectNotFoundException;

    /**
     * 获取项目的全部子项目
     * @param projectBelongId
     * @return
     */
    List<Project> findProjectProjects(long projectBelongId);

    /**
     * 获取项目的全部子任务
     * @param projectBelongId
     * @return
     */
    List<Task> findProjectTasks(long projectBelongId);

}
