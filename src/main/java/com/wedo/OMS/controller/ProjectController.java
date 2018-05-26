package com.wedo.OMS.controller;

import com.wedo.OMS.entity.Project;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.exception.ProjectNotFoundException;
import com.wedo.OMS.service.ProjectService;
import org.springframework.web.bind.annotation.*;

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
     * @param project 新建项目的相关信息
     * @param belong  新建项目所属的项目id
     */
    @PostMapping(value = "/projects")
    public Project addProject(@RequestBody Project project, @RequestBody long belong) throws ProjectNotFoundException {
        return projectService.addProject(project, belong);
    }

    /**
     * 用户获取单个项目信息
     *
     * @param projectId
     * @return
     */
    @GetMapping(value = "/projects/:projectId")
    public Project getProject(@PathVariable("projectId") long projectId) throws ProjectNotFoundException {
        return projectService.getProjectsByProjectId(projectId);
    }

    /**
     * 获取项目的全部子项目
     * @param projectId
     * @return
     */
    @GetMapping(value = "/projects/:projectId/projects")
    public List<Project> getProjectProjects(@PathVariable("projectId") long projectId){
        return projectService.findProjectProjects(projectId);
    }

    /**
     * 获取项目的全部子任务
     * @param projectId
     * @return
     */
    @GetMapping(value = "/projects/:projectId/tasks")
    public List<Task> getProjectTasks(@PathVariable("projectId") long projectId){
        return projectService.findProjectTasks(projectId);
    }

}
