package com.wedo.OMS.service;

import com.wedo.OMS.entity.Milestone;
import com.wedo.OMS.entity.Result;
import com.wedo.OMS.enums.MilestoneStatus;
import com.wedo.OMS.enums.VerifyStatus;

import java.util.List;

public interface MilestoneService {
    /**
     * 根据任务ID查看里程碑
     * @param taskId
     * @return
     */
    List<Milestone> listMilestonesByTaskId(Long taskId);

    /**
     * 查看里程碑详情
     * @param milestoneId
     * @return
     */
    Milestone getMilestoneByMilestoneId(Long milestoneId);

    /**
     * 查看里程碑成果
     * @param milestoneId
     * @return
     */
    List<Result> getMilestoneResultsByMilestoneId(Long milestoneId);

    /**
     * 接包方上传成果
     *
     * @param milestoneId
     * @param result
     */
    void uploadResult(Long milestoneId,Result result);

    /**
     * 接包方下载成果
     * @param resultId
     */
    void downloadResult(Long resultId);

    /**
     * 审核里程碑,pass or notpass
     * @param milestoneId
     * @param status pass or notpass
     */
    void auditMilestoneByMilestoneId(Long milestoneId,MilestoneStatus status);
}
