package com.wedo.OMS.controller;

import com.wedo.OMS.entity.Milestone;
import com.wedo.OMS.enums.MilestoneStatus;
import com.wedo.OMS.service.MilestoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MilestoneController {
    private MilestoneService milestoneService;
    public MilestoneController(MilestoneService milestoneService){
        this.milestoneService = milestoneService;
    }

    /**
     * 用户新建里程碑
     * @param milestones
     * @param taskId
     * @return
     */
    @PostMapping(value = "/tasks/:taskId/milestones")
    public List<Milestone> addMilestone(@RequestBody List<Milestone> milestones, @PathVariable("taskId") long taskId){
        return milestoneService.addMilestone(milestones,taskId);
    }

    /**
     * 获取任务全部里程碑
     * @param taskId
     * @return
     */
    @GetMapping(value = "/tasks/:taskId/milestones")
    public List<Milestone> listMilestonesByTaskId(@PathVariable("taskId") long taskId){
        return milestoneService.listMilestonesByTaskId(taskId);
    }

    /**
     * 获取单个里程碑信息
     * @param milestoneId
     * @return
     */
    @GetMapping(value = "/milestones/:milestoneId")
    public Milestone getMilestoneByMilestoneId(@PathVariable("milestoneId") long milestoneId){
        return milestoneService.getMilestoneByMilestoneId(milestoneId);
    }

    /**
     * 审核里程碑
     * @param milestoneId
     * @param status
     * @return
     */
    @PatchMapping(value = "/milestones/:milestoneId")
    public Milestone auditMilestoneByMilestoneId(@PathVariable("milestoneId")long milestoneId,@RequestBody MilestoneStatus status){
        return milestoneService.auditMilestoneByMilestoneId(milestoneId, status);
    }

}
