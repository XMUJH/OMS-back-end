package com.wedo.OMS.service;

import com.wedo.OMS.entity.Milestone;
import com.wedo.OMS.entity.Result;
import com.wedo.OMS.enums.MilestoneStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MilestoneServiceImpl implements MilestoneService{
    @Override
    public List<Milestone> listMilestonesByTaskId(Long taskId) {
        return null;
    }

    @Override
    public Milestone getMilestoneByMilestoneId(Long milestoneId) {
        return null;
    }

    @Override
    public List<Result> getMilestoneResultsByMilestoneId(Long milestoneId) {
        return null;
    }

    @Override
    public void uploadResult(Long taskId, Result result) {

    }

    @Override
    public void downloadResult(Long resultId) {

    }

    @Override
    public void auditMilestoneByMilestoneId(Long milestoneId, MilestoneStatus status) {

    }
}
