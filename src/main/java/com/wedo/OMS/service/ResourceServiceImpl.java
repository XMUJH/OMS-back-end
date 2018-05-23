package com.wedo.OMS.service;

import com.wedo.OMS.entity.Resource;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.entity.TaskResource;
import com.wedo.OMS.repository.ResourceRepository;
import com.wedo.OMS.repository.TaskRepository;
import com.wedo.OMS.repository.TaskResourceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    private ResourceRepository resourceRepository;
    private TaskResourceRepository taskResourceRepository;
    private TaskRepository taskRepository;

    public ResourceServiceImpl(ResourceRepository resourceRepository, TaskResourceRepository taskResourceRepository, TaskRepository taskRepository){
        this.resourceRepository=resourceRepository;
        this.taskResourceRepository=taskResourceRepository;
        this.taskRepository=taskRepository;
    }

    @Override
    public List<Resource> listResourcesByTaskId(long taskId) {
        Task task=taskRepository.findTaskById(taskId);
        List<TaskResource> allTaskResources;
        List<Resource> result=new ArrayList<>();
        allTaskResources=taskResourceRepository.findAll();
        for(TaskResource i : allTaskResources)
        {
            if(i.getTask()==task)
            {
                result.add(i.getResource());
            }
        }
        return result;
    }

    @Override
    public List<Resource> listAllResources() {
        return resourceRepository.findAll();
    }

    @Override
    public Resource addResource(Resource resource, List<Task> tasks) {
        resourceRepository.save(resource);
        TaskResource taskResource;
        for(Task task : tasks)
        {
            taskResource=new TaskResource();
            taskResource.setResource(resource);
            taskResource.setTask(task);
            taskResourceRepository.save(taskResource);
        }
        return resource;
    }

    /*
    @Override
    public void updateResource(Long resourceId, Resource resource, List<Task> tasks) {

    }
    */

    @Override
    public void deleteResourceById(long resourceId) {
        Resource resource=resourceRepository.findResourceById(resourceId);
        List<TaskResource> taskResources=taskResourceRepository.findTaskResourcesByResource(resource);
        for(TaskResource i : taskResources)
        {
            taskResourceRepository.delete(i);
        }
        resourceRepository.deleteById(resourceId);
    }

    @Override
    public Resource getResourceByResourceId(long resourceId) {
        return resourceRepository.findResourceById(resourceId);
    }

    @Override
    public List<Resource> getResourcesByResourcename(String resourceName) {
        List<Resource> resources=resourceRepository.findAll();
        List<Resource> result=new ArrayList<>();
        for(Resource resource : resources)
        {
            if(resource.getName().indexOf(resourceName)!=-1)
            {
                result.add(resource);
            }
        }
        return result;
    }

    @Override
    public Resource uploadResource(long taskId, Resource resource) {
        List<Task> tasks=new ArrayList<>();
        tasks.add(taskRepository.findTaskById(taskId));
        addResource(resource,tasks);
        return resource;
    }

    @Override
    public String downloadResource(long resourceId) {
        return resourceRepository.findResourceById(resourceId).getAddress();
    }

    /**
     * 分配资源(依次分配)
     * @param resourceId
     * @param taskName
     * @return
     */
    @Override
    public TaskResource addTaskResource(long resourceId,String taskName){
        taskRepository.findTaskByName(taskName);
        Resource resource = resourceRepository.findResourceById(resourceId);
        Task task = taskRepository.findTaskByName(taskName);
        TaskResource taskResource = new TaskResource();
        taskResource.setResource(resource);
        taskResource.setTask(task);
        taskResourceRepository.save(taskResource);
        return taskResource;
    }
}
