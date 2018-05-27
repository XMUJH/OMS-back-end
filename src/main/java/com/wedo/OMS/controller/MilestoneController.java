package com.wedo.OMS.controller;

import com.wedo.OMS.entity.Milestone;
import com.wedo.OMS.entity.MilestoneHistory;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.enums.MilestoneStatus;
import com.wedo.OMS.exception.MilestoneNotFoundException;
import com.wedo.OMS.exception.TaskNotFoundException;
import com.wedo.OMS.service.MilestoneService;
import com.wedo.OMS.vo.AuditMilestone;
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
     * 用户更新里程碑
     *
     * @param milestone
     * @param milestoneId
     * @return
     */
    @PostMapping(value = "/milestones/{milestoneId}")
    public void updateMilestone(@RequestBody Milestone milestone, @PathVariable("milestoneId") long milestoneId) throws TaskNotFoundException {
        milestoneService.updateMilestone(milestone,milestoneId);
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
     * 审核里程碑成果
     *
     * @param milestoneId
     * @param auditMilestone
     * @return
     */
    @PatchMapping(value = "/milestones/{milestoneId}")
    public Milestone auditMilestoneByMilestoneId(@PathVariable("milestoneId") long milestoneId,@RequestBody AuditMilestone auditMilestone) throws MilestoneNotFoundException {
        Milestone milestone = milestoneService.getMilestoneByMilestoneId(milestoneId);
        MilestoneHistory milestoneHistory = new MilestoneHistory();
        milestoneHistory.setCreateTime(auditMilestone.getDate());
        milestoneHistory.setReason(auditMilestone.getReason());
        milestoneHistory.setMilestone(milestone);
        switch (auditMilestone.getStatus()){
            case "PASS":
                milestone = milestoneService.auditMilestoneByMilestoneId(milestoneId, MilestoneStatus.PASS);
                milestoneHistory.setStatus(1);
                milestoneService.saveNewMilestoneHistory(milestoneHistory);
                break;
            case "NOTPASS":
                milestone = milestoneService.auditMilestoneByMilestoneId(milestoneId, MilestoneStatus.NOTPASS);
                milestoneHistory.setStatus(-1);
                milestoneService.saveNewMilestoneHistory(milestoneHistory);
                break;
        }
        return milestone;
    }

    /**
     * 获取某里程碑的全部成果审核记录(按照提交时间排序)
     * @param milestoneId
     * @return
     */
    @GetMapping(value = "/milestones/{milestoneId}/milestoneHistories")
    public List<MilestoneHistory> getMilestoneHistoriesByMilestoneId(@PathVariable("milestoneId") long milestoneId){
        List<MilestoneHistory> milestoneHistories = milestoneService.getMilestoneHistoriesByMilestoneId(milestoneId);
        milestoneHistories = milestoneService.sortMilestoneHistoriesByTime(milestoneHistories);
        return milestoneHistories;
    }

    /**
     * 获取最新审核记录
     * @param taskId
     * @return
     */
    @GetMapping(value = "/tasks/{taskId}/milestoneHistory")
    public MilestoneHistory getCurrentMilestoneHistory(@PathVariable("taskId") long taskId){
        List<MilestoneHistory> milestoneHistories = milestoneService.getMilestoneHistoriesByTaskId(taskId);
        milestoneHistories = milestoneService.sortMilestoneHistoriesByTime(milestoneHistories);
        return milestoneHistories.get(milestoneHistories.size()-1);
    }
}
