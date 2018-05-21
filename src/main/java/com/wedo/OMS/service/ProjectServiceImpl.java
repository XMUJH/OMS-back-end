package com.wedo.OMS.service;

import com.wedo.OMS.entity.Project;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.repository.ProjectRepository;
import com.wedo.OMS.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository,TaskRepository taskRepository){
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    /**
     * 新增项目
     * @param project
     * @param belong 所属项目的ID
     * @return
     */
    @Override
    public void addProject(Project project, Long belong) {
        Project Belong = projectRepository.findProjectById(belong);
        project.setBelong(Belong);
        projectRepository.save(project);
    }

    /**
     * 将项目移入另一个项目中
     * @param InprojectId
     * @param OutprojectId
     * @return
     */
    @Override
    public void MoveInprojectToOutprojectByProjectId(Long InprojectId,Long OutprojectId) {
        Project Inproject = projectRepository.findProjectById(InprojectId);
        Project Outproject = projectRepository.findProjectById(OutprojectId);
        Inproject.setBelong(Outproject);
        Outproject.setCompletion(Outproject.getCompletion()+Inproject.getCompletion());
        Outproject.setTotal(Outproject.getTotal()+Inproject.getTotal());
        projectRepository.save(Inproject);
        projectRepository.save(Outproject);
    }

    /**
     * 将任务移入项目中
     * @param taskId
     * @param projectId
     * @return
     */
    public void MoveTaskToProjectById(Long taskId,Long projectId){
        Task task = taskRepository.findTaskById(taskId);
        Project project = projectRepository.findProjectById(projectId);
        task.setProject(project);
        project.setCompletion(project.getCompletion()+task.getCompletion());
        project.setTotal(project.getTotal()+task.getTotal());
        projectRepository.save(project);
        taskRepository.save(task);
    }

    /**
     * 计算进度
     * @param projectId
     * @return 百分数
     */
    public long CalScheduleByProjectId(Long projectId){
        Project project = projectRepository.findProjectById(projectId);
        return project.getCompletion()*100/project.getTotal();
    }

    /**
     * 删除项目
     * @param projectId
     */
    @Override
    public void deleteProjectByProjectId(Long projectId) {

    }
}
