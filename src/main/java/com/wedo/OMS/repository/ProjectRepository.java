package com.wedo.OMS.repository;

import com.wedo.OMS.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findProjectById(long projectId);
    List<Project> findProjectsByBelong(Project project);
}
