package com.wedo.OMS.controller;

import com.wedo.OMS.entity.Result;
import com.wedo.OMS.service.MilestoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResultController {
    private MilestoneService milestoneService;
    public ResultController(MilestoneService milestoneService){
        this.milestoneService = milestoneService;
    }

    /**
     * 获取里程碑的全部成果
     * @param milestoneId
     * @return
     */
    @GetMapping(value = "/milestones/{milestoneId}/results")
    public List<Result> getResultsByMilestone(@PathVariable("milestoneId")long milestoneId){
        return milestoneService.getResultsByMilestone(milestoneId);
    }

    /**
     * 上传成果
     * @param milestoneId
     * @return
     */
    @PostMapping(value = "/milestones/{milestoneId}/results")
    public void newResult(@PathVariable("milestoneId")long milestoneId, @RequestBody Result result){
        milestoneService.newResult(result,milestoneId);
    }
}
