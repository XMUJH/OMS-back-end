package com.wedo.OMS.controller;

import com.wedo.OMS.entity.Project;
import com.wedo.OMS.service.ProjectService;
import org.springframework.web.bind.annotation.*;

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
    public Project addProject(@RequestBody Project project, @RequestBody long belong) {
        return projectService.addProject(project, belong);
    }

    /**
     * 用户获取单个项目信息
     *
     * @param projectId
     * @return
     */
    @GetMapping(value = "/projects/:projectId")
    public Project getProject(@PathVariable("projectId") long projectId) {
        return projectService.getProjectsByProjectId(projectId);
    }

}
