package com.wedo.OMS.service;

import com.wedo.OMS.entity.Project;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.exception.ProjectNotFoundException;
import com.wedo.OMS.exception.TaskNotFoundException;
import com.wedo.OMS.repository.ProjectRepository;
import com.wedo.OMS.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    /**
     * 新增项目
     *
     * @param project
     * @param belong  所属项目的ID
     * @return
     */
    @Override
    public Project addProject(Project project, long belong) throws ProjectNotFoundException {
        Project belongedProject = projectRepository.findProjectById(belong);
        if (belongedProject == null) {
            throw new ProjectNotFoundException();
        }
        project.setBelong(belongedProject);
        projectRepository.save(project);
        return project;
    }

    /**
     * 将项目移入另一个项目中
     *
     * @param inProjectId
     * @param outProjectId
     * @return
     */
    @Override
    public void MoveInProjectToOutProjectByProjectId(long inProjectId, long outProjectId) throws ProjectNotFoundException {
        Project inProject = projectRepository.findProjectById(inProjectId);
        Project outProject = projectRepository.findProjectById(outProjectId);
        if (inProject == null || outProject == null) {
            throw new ProjectNotFoundException();
        }
        inProject.setBelong(outProject);
        outProject.setCompletion(outProject.getCompletion() + inProject.getCompletion());
        outProject.setTotal(outProject.getTotal() + inProject.getTotal());
        projectRepository.save(inProject);
        projectRepository.save(outProject);
    }

    /**
     * 将任务移入项目中
     *
     * @param taskId
     * @param projectId
     * @return
     */
    @Override
    public Task MoveTaskToProjectById(long taskId, long projectId) throws TaskNotFoundException, ProjectNotFoundException {
        Task task = taskRepository.findTaskById(taskId);
        Project project = projectRepository.findProjectById(projectId);
        if (task == null) {
            throw new TaskNotFoundException();
        }
        if (project == null) {
            throw new ProjectNotFoundException();
        }
        task.setProject(project);
        project.setCompletion(project.getCompletion() + task.getCompletion());
        project.setTotal(project.getTotal() + task.getTotal());
        projectRepository.save(project);
        taskRepository.save(task);
        return task;
    }

    /**
     * 计算进度
     *
     * @param projectId
     * @return 百分数
     */
    @Override
    public long CalScheduleByProjectId(long projectId) throws ProjectNotFoundException {
        Project project = projectRepository.findProjectById(projectId);
        if (project == null) {
            throw new ProjectNotFoundException();
        }
        return project.getCompletion() * 100 / project.getTotal();
    }

    /**
     * 删除项目
     *
     * @param projectId
     */
    @Override
    public void deleteProjectByProjectId(long projectId) {

    }

    /**
     * 获取项目信息
     *
     * @param projectId
     * @return
     */
    @Override
    public Project getProjectByProjectId(long projectId) throws ProjectNotFoundException {
        Project project = projectRepository.findProjectById(projectId);
        if (project == null) {
            throw new ProjectNotFoundException();
        }
        return project;
    }

    /**
     * 获取项目的全部子项目
     * @param projectBelongId
     * @return
     */
    @Override
    public List<Project> findProjectProjects(long projectBelongId){
        Project projectBelong = projectRepository.findProjectById(projectBelongId);
        return projectRepository.findProjectsByBelong(projectBelong);
    }

    /**
     * 获取项目的全部子任务
     * @param projectBelongId
     * @return
     */
    @Override
    public List<Task> findProjectTasks(long projectBelongId){
        Project projectBelong =projectRepository.findProjectById(projectBelongId);
        return taskRepository.findTasksByProject(projectBelong);

    }

    @Override
    public Project findProjectByProjectId(long projectId)
    {
        return projectRepository.findProjectById(projectId);
    }
}
