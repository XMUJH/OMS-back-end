package com.wedo.OMS.controller;

import com.wedo.OMS.entity.Project;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.exception.ProjectNotFoundException;
import com.wedo.OMS.exception.TaskNotFoundException;
import com.wedo.OMS.service.ProjectService;
import com.wedo.OMS.vo.NewProject;
import com.wedo.OMS.vo.OneLong;
import com.wedo.OMS.vo.vue_task;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.ArrayList;
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
        return projectService.getProjectByProjectId(projectId);
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
    public List<vue_task> getProjectTasks(@PathVariable("projectId") long projectId){
        List<Task> tasks=projectService.findProjectTasks(projectId);
        vue_task result;
        List<vue_task> vue_tasks=new ArrayList<vue_task>();
        long percentage;
        for(Task task : tasks)
        {
            result=new vue_task();
            result.setId(task.getId());
            result.setName(task.getName());
            if(task.getTotal()==0) percentage=0;
            else percentage = task.getCompletion()*100/task.getTotal();
            result.setPercentage(percentage);
            result.setTaskColor(result.percentToColor(percentage));
            vue_tasks.add(result);
        }
        return vue_tasks;
    }
    /**
     * 用户通过拖曳的方式将项目放进一个项目里
     *
     * @param projectId    项目id
     * @param projectId2 目标项目id
     */
    @PatchMapping(value = "/projects/{projectId}")
    public void MoveProjectToProjectById(@PathVariable("projectId") long projectId, @RequestBody OneLong projectId2) throws ProjectNotFoundException, TaskNotFoundException {
        long id = projectId2.getId();
        projectService.MoveInProjectToOutProjectByProjectId(projectId, id);
    }
}
