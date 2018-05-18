package com.wedo.OMS.service;

import com.wedo.OMS.entity.Milestone;
import com.wedo.OMS.entity.Result;
import com.wedo.OMS.enums.MilestoneStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MilestoneServiceImpl implements MilestoneService{

    private MilestoneRepository milestoneRepository;
    private TaskRepository taskRepository;
    private ResultRepository resultRepository;

    public MilestoneServiceImpl(MilestoneRepository milestoneRepository, TaskRepository taskRepository, ResultRepository resultRepository) {
        this.milestoneRepository = milestoneRepository;
        this.taskRepository = taskRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public List<Milestone> listMilestonesByTaskId(Long taskId) {
        Task task = taskRepository.findAllById(taskId);
        List<Milestone> milestones = milestoneRepository.findAllByTask(task);
        return milestones;
    }

    @Override
    public Milestone getMilestoneByMilestoneId(Long milestoneId) {
        Milestone milestone = milestoneRepository.findAllById(milestoneId);
        return milestone;
    }

    @Override
    public List<Result> getMilestoneResultsByMilestoneId(Long milestoneId) {
        Milestone milestone = milestoneRepository.findAllById(milestoneId);
        List<Result> results = resultRepository.findAllByMilestone(milestone);
        return results;
    }

    @Override
    public void uploadResult(Long milestoneId, Result result) {
        Milestone milestone = milestoneRepository.findAllById(milestoneId);
        result.setMilestone(milestone);
        resultRepository.save(result);
    }

    @Override
    public void downloadResult(Long resultId) {

    }

    @Override
    public void auditMilestoneByMilestoneId(Long milestoneId, MilestoneStatus status) {
        Milestone milestone = milestoneRepository.findAllById(milestoneId);
        milestone.setStatus(status);
        milestoneRepository.save(milestone);
    }
}
