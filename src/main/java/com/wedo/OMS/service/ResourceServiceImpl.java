package com.wedo.OMS.service;

import com.wedo.OMS.entity.*;
import com.wedo.OMS.exception.ResourceNotFoundException;
import com.wedo.OMS.exception.TaskNotFoundException;
import com.wedo.OMS.exception.UserNotFoundException;
import com.wedo.OMS.repository.*;
import com.wedo.OMS.vo.ProjectViewModel;
import com.wedo.OMS.vo.ResourceViewModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    private final ResourceRepository resourceRepository;
    private TaskResourceRepository taskResourceRepository;
    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private UserTaskRepository userTaskRepository;
    private ProjectRepository projectRepository;

    public ResourceServiceImpl(ProjectRepository projectRepository, UserTaskRepository userTaskRepository, UserRepository userRepository, ResourceRepository resourceRepository, TaskResourceRepository taskResourceRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.userTaskRepository = userTaskRepository;
        this.userRepository = userRepository;
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
    public List<TaskResource> listTaskResourcesByUserId(long userId) throws UserNotFoundException {
        User user = userRepository.findUserById(userId);
        List<UserTask> userTasks = userTaskRepository.findUserTasksByUser(user);
        List<Task> tasks = new ArrayList<>();
        List<TaskResource> taskResources = new ArrayList<>();
        for (UserTask ut : userTasks) {
            tasks.add(ut.getTask());
        }
        for (Task task : tasks) {
            taskResources.addAll(taskResourceRepository.findTaskResourcesByTask(task));
        }
        return taskResources;
    }

    @Override
    public List<ResourceViewModel> listAllResourcesViewModel() {
        List<Resource> resources = resourceRepository.findAll();
        List<ResourceViewModel> resourceViewModels = new ArrayList<>();

        for (Resource r : resources) {
            List<TaskResource> taskResources = taskResourceRepository.findTaskResourcesByResource(r);
            ResourceViewModel resourceViewModel = new ResourceViewModel();
            resourceViewModel.setResourceName(r.getName());
            List<Task> tasks = new ArrayList<>();
            for (TaskResource tr : taskResources) {
                tasks.add(tr.getTask());
            }
            resourceViewModel.setBelong(tasks);
            resourceViewModel.setSafetyLevel(r.getSafety());
            resourceViewModels.add(resourceViewModel);
        }
        return resourceViewModels;

    }

    public Resource addResource(String filename) {
        Resource resource = new Resource();
        resource.setAddress("resource/" + filename);
        resource.setCommit(new Date());
        resource.setName(filename);
        resourceRepository.save(resource);
        return resource;

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
     * @param taskId
     * @return
     */
    @Override
    public TaskResource addTaskResource(long resourceId, long taskId) throws ResourceNotFoundException, TaskNotFoundException {
        Resource resource = resourceRepository.findResourceById(resourceId);
        Task task = taskRepository.findTaskById(taskId);
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

    @Override
    public List<TaskResource> addTaskResource(long resourceId, List<ProjectViewModel> projectViewModels) throws ResourceNotFoundException, TaskNotFoundException {
        List<Task> tasks = new ArrayList<>();
        for (ProjectViewModel pr : projectViewModels) {
            tasks.addAll(getTasksFromProjectViewModel(tasks, pr));
        }
        List<TaskResource> taskResources = new ArrayList<>();
        for (Task task : tasks) {
            taskResources.add(addTaskResource(resourceId, task.getId()));
        }
        return taskResources;
    }

    @Override
    public List<Task> getTasksFromProjectViewModel(List<Task> tasks, ProjectViewModel projectViewModel) {
        if (projectViewModel.getType() == 1) {
            tasks.add(taskRepository.findTaskById(projectViewModel.getId()));
        } else {
            for (ProjectViewModel pr : projectViewModel.getChildren()) {
                tasks = getTasksFromProjectViewModel(tasks, pr);
            }
        }
        return tasks;

    }
}
