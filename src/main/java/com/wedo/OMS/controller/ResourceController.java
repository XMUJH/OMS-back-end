package com.wedo.OMS.controller;

import com.wedo.OMS.entity.Resource;
import com.wedo.OMS.entity.TaskResource;
import com.wedo.OMS.exception.ResourceNotFoundException;
import com.wedo.OMS.exception.TaskNotFoundException;
import com.wedo.OMS.exception.UserNotFoundException;
import com.wedo.OMS.service.ResourceService;
import com.wedo.OMS.vo.ResourceViewModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResourceController {
    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * 管理员删除资源
     *
     * @param resourceId
     */
    @DeleteMapping(value = "/resources/{resourceId}")
    public void deleteResourceById(@PathVariable("resourceId") long resourceId) {
        resourceService.deleteResourceById(resourceId);
    }

    /**
     * 接包方获取所有资源
     *
     * @param userId
     * @return
     * @throws UserNotFoundException
     */
    @GetMapping(value = "/users/{userId}/resources")
    public List<TaskResource> listTaskResourcesByUserId(@PathVariable("userId") long userId) throws UserNotFoundException {
        return resourceService.listTaskResourcesByUserId(userId);
    }
    /**
     * 管理员分配资源（前端循环语句依次分配）
     *
     * @param resourceId
     * @param taskID
     * @return
     */
    @PostMapping(value = "/resources/{resourceId}/tasks/{taskID}")
    public TaskResource addResource(@PathVariable("resourceId") long resourceId, @PathVariable("taskID") long taskID) throws ResourceNotFoundException, TaskNotFoundException {
        return resourceService.addTaskResource(resourceId, taskID);
    }

    /**
     * 获取该任务所有资源
     *
     * @param taskId
     * @return
     */
    @GetMapping(value = "/tasks/{taskId}/resources")
    public List<Resource> listResourcesByTaskId(@PathVariable("taskId") long taskId) throws TaskNotFoundException {
        return resourceService.listResourcesByTaskId(taskId);
    }

    /**
     * 发包方获取所有资源
     *
     * @return
     */
    @GetMapping(value = "/resources")
    public List<ResourceViewModel> listAllResourcesViewModel() {
        return resourceService.listAllResourcesViewModel();
    }


}
