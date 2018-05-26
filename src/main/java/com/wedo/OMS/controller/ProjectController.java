package com.wedo.OMS.controller;

import com.wedo.OMS.entity.Project;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.exception.ProjectNotFoundException;
import com.wedo.OMS.service.ProjectService;
import com.wedo.OMS.vo.NewProject;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

@RestController
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * 新建项目
     *
     * @param newProject  新建项目的相关信息及新建项目所属的项目id
     */
    @PostMapping(value = "/projects")
    public Project addProject(@RequestBody NewProject newProject) throws ProjectNotFoundException {
        Project project = new Project();
        project.setCompletion(newProject.getCompletion());
        project.setTotal(newProject.getTotal());
        project.setName(newProject.getName());
        project.setPassword(newProject.getPassword());
        return projectService.addProject(project, newProject.getBelongProjectId());
    }

    /**
     * 用户获取单个项目信息
     *
     * @param projectId
     * @return
     */
    @GetMapping(value = "/projects/{projectId}")
    public Project getProject(@PathVariable("projectId") long projectId) throws ProjectNotFoundException {
        return projectService.getProjectsByProjectId(projectId);
    }

    /**
     * 获取项目的全部子项目
     * @param projectId
     * @return
     */
    @GetMapping(value = "/projects/{projectId}/projects")
    public List<Project> getProjectProjects(@PathVariable("projectId") long projectId){
        return projectService.findProjectProjects(projectId);
    }

    /**
     * 获取项目的全部子任务
     * @param projectId
     * @return
     */
    @GetMapping(value = "/projects/{projectId}/tasks")
    public List<Task> getProjectTasks(@PathVariable("projectId") long projectId){
        return projectService.findProjectTasks(projectId);
    }

}
