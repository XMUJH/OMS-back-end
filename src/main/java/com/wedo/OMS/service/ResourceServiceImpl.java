package com.wedo.OMS.service;

import com.wedo.OMS.entity.Resource;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.entity.TaskResource;
import com.wedo.OMS.exception.ResourceNotFoundException;
import com.wedo.OMS.exception.TaskNotFoundException;
import com.wedo.OMS.repository.ResourceRepository;
import com.wedo.OMS.repository.TaskRepository;
import com.wedo.OMS.repository.TaskResourceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    private final ResourceRepository resourceRepository;
    private TaskResourceRepository taskResourceRepository;
    private TaskRepository taskRepository;

    public ResourceServiceImpl(ResourceRepository resourceRepository, TaskResourceRepository taskResourceRepository, TaskRepository taskRepository) {
        this.resourceRepository = resourceRepository;
        this.taskResourceRepository = taskResourceRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Resource> listResourcesByTaskId(long taskId) throws TaskNotFoundException {
        Task task = taskRepository.findTaskById(taskId);
        if (task == null) {
            throw new TaskNotFoundException();
        }
        List<TaskResource> allTaskResources = taskResourceRepository.findAll();
        List<Resource> result = new ArrayList<>();
        for (TaskResource resource : allTaskResources) {
            if (resource.getTask() == task) {
                result.add(resource.getResource());
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
        for (Task task : tasks) {
            TaskResource taskResource = new TaskResource();
            taskResource.setResource(resource);
            taskResource.setTask(task);
            taskResourceRepository.save(taskResource);
        }
        return resource;
    }

    @Override
    public void deleteResourceById(long resourceId) {
        Resource resource = resourceRepository.findResourceById(resourceId);
        List<TaskResource> taskResources = taskResourceRepository.findTaskResourcesByResource(resource);
        for (TaskResource i : taskResources) {
            taskResourceRepository.delete(i);
        }
        resourceRepository.deleteById(resourceId);
    }

    @Override
    public Resource getResourceByResourceId(long resourceId) throws ResourceNotFoundException {
        Resource resource = resourceRepository.findResourceById(resourceId);
        if (resource == null) {
            throw new ResourceNotFoundException();
        }
        return resource;
    }

    @Override
    public List<Resource> getResourcesByResourceName(String resourceName) {
        List<Resource> resources = resourceRepository.findAll();
        List<Resource> result = new ArrayList<>();
        for (Resource resource : resources) {
            if (resource.getName().contains(resourceName)) {
                result.add(resource);
            }
        }
        return result;
    }

    @Override
    public Resource uploadResource(long taskId, Resource resource) throws TaskNotFoundException {
        Task task = taskRepository.findTaskById(taskId);
        if (task == null) {
            throw new TaskNotFoundException();
        }
        addResource(resource, Collections.singletonList(task));
        return resource;
    }

    @Override
    public String downloadResource(long resourceId) {
        return resourceRepository.findResourceById(resourceId).getAddress();
    }

    /**
     * 分配资源(依次分配)
     *
     * @param resourceId
     * @param taskName
     * @return
     */
    @Override
    public TaskResource addTaskResource(long resourceId, String taskName) throws ResourceNotFoundException, TaskNotFoundException {
        Resource resource = resourceRepository.findResourceById(resourceId);
        Task task = taskRepository.findTaskByName(taskName);
        if (resource == null) {
            throw new ResourceNotFoundException();
        }
        if (task == null) {
            throw new TaskNotFoundException();
        }
        TaskResource taskResource = new TaskResource();
        taskResource.setResource(resource);
        taskResource.setTask(task);
        taskResourceRepository.save(taskResource);
        return taskResource;
    }
}
