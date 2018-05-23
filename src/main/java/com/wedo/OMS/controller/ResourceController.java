package com.wedo.OMS.controller;

import com.wedo.OMS.entity.Resource;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.entity.TaskResource;
import com.wedo.OMS.service.ResourceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResourceController {
    private ResourceService resourceService;
    public ResourceController(ResourceService resourceService){
        this.resourceService = resourceService;
    }

    /**
     * 管理员删除资源
     * @param resourceId
     */
    @DeleteMapping(value = "/resources/:resourceId")
    public void deleteResourceById(@PathVariable("resourceId") long resourceId){
        resourceService.deleteResourceById(resourceId);
    }

    /**
     * 管理员分配资源（前端循环语句依次分配）
     * @param resourceId
     * @param taskName
     * @return
     */
    @PostMapping(value = "/resources/:resourceId/tasks/:taskName")
    public TaskResource addResource(@PathVariable("resourceId") long resourceId, @PathVariable("taskName")String taskName){
        return resourceService.addTaskResource(resourceId,taskName);
    }

}
