package com.wedo.OMS.service;

import com.wedo.OMS.entity.Resource;
import com.wedo.OMS.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Override
    public List<Resource> listResourcesByTaskId(Long taskId) {
        return null;
    }

    @Override
    public List<Resource> listAllResources() {
        return null;
    }

    @Override
    public Resource addResource(Resource resource, List<Task> tasks) {
        return null;
    }

    @Override
    public void updateResource(Long resourceId, Resource resource, List<Task> tasks) {

    }

    @Override
    public void deleteResourceById(Long resourceId) {

    }

    @Override
    public Resource getResourceByResourceId(Long resourceId) {
        return null;
    }

    @Override
    public List<Resource> getResourcesByResourcename(Long resourcename) {
        return null;
    }

    @Override
    public void uploadResource(Long taskId, Resource resource) {

    }

    @Override
    public void downloadResource(Long resourceId) {

    }
}
