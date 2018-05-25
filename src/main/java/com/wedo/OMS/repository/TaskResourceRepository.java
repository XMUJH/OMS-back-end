package com.wedo.OMS.repository;

import com.wedo.OMS.entity.Resource;
import com.wedo.OMS.entity.TaskResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskResourceRepository extends JpaRepository<TaskResource, Long> {
    List<TaskResource> findTaskResourcesByResource(Resource resource);
}
