package com.wedo.OMS.controller;

import com.wedo.OMS.entity.Resource;
import com.wedo.OMS.entity.Task;
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
    @DeleteMapping(value = "/resources/:id")
    public void deleteResourceById(@PathVariable("resourceId") long resourceId){
        resourceService.deleteResourceById(resourceId);
    }

    @PostMapping(value = "/resources/:id/tasks/:name")
    public Resource addResource(@PathVariable("resourceId") long resourceId, @PathVariable("taskName")String name){
        return null;
    }

}
