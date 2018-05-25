package com.wedo.OMS.controller;

import com.wedo.OMS.entity.Milestone;
import com.wedo.OMS.enums.MilestoneStatus;
import com.wedo.OMS.exception.MilestoneNotFoundException;
import com.wedo.OMS.exception.TaskNotFoundException;
import com.wedo.OMS.service.MilestoneService;
import com.wedo.OMS.vo.EnumChoice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MilestoneController {
    private final MilestoneService milestoneService;

    public MilestoneController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    /**
     * 用户新建里程碑
     *
     * @param milestones
     * @param taskId
     * @return
     */
    @PostMapping(value = "/tasks/{taskId}/milestones")
    public List<Milestone> addMilestone(@RequestBody List<Milestone> milestones, @PathVariable("taskId") long taskId) throws TaskNotFoundException {
        return milestoneService.addMilestone(milestones, taskId);
    }

    /**
     * 获取任务全部里程碑
     *
     * @param taskId
     * @return
     */
    @GetMapping(value = "/tasks/{taskId}/milestones")
    public List<Milestone> listMilestonesByTaskId(@PathVariable("taskId") long taskId) throws TaskNotFoundException {
        return milestoneService.listMilestonesByTaskId(taskId);
    }

    /**
     * 获取单个里程碑信息
     *
     * @param milestoneId
     * @return
     */
    @GetMapping(value = "/milestones/{milestoneId}")
    public Milestone getMilestoneByMilestoneId(@PathVariable("milestoneId") long milestoneId) {
        return milestoneService.getMilestoneByMilestoneId(milestoneId);
    }

    /**
     * 审核里程碑
     *
     * @param milestoneId
     * @param enumChoice
     * @return
     */
    @PatchMapping(value = "/milestones/{milestoneId}")
    public Milestone auditMilestoneByMilestoneId(@PathVariable("milestoneId") long milestoneId,@RequestBody EnumChoice enumChoice) throws MilestoneNotFoundException {
        Milestone milestone = new Milestone();
        switch (enumChoice.getChoice()){
            case "PASS":
                milestone = milestoneService.auditMilestoneByMilestoneId(milestoneId, MilestoneStatus.PASS);
                break;
            case "NOTPASS":
                milestone = milestoneService.auditMilestoneByMilestoneId(milestoneId, MilestoneStatus.NOTPASS);
                break;
            case "NOTBEGIN":
                milestone = milestoneService.auditMilestoneByMilestoneId(milestoneId, MilestoneStatus.NOTBEGIN);
                break;
        }
        return milestone;
    }

}
