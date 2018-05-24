package com.wedo.OMS.service;

import com.wedo.OMS.entity.Milestone;
import com.wedo.OMS.entity.Result;
import com.wedo.OMS.enums.MilestoneStatus;
import com.wedo.OMS.exception.MilestoneNotFoundException;
import com.wedo.OMS.exception.ResultNotFoundException;
import com.wedo.OMS.exception.TaskNotFoundException;

import java.util.List;

public interface MilestoneService {
    /**
     * 根据任务ID查看里程碑
     *
     * @param taskId
     * @return
     */
    List<Milestone> listMilestonesByTaskId(long taskId) throws TaskNotFoundException;

    /**
     * 查看里程碑详情
     *
     * @param milestoneId
     * @return
     */
    Milestone getMilestoneByMilestoneId(long milestoneId);

    /**
     * 查看里程碑成果
     *
     * @param milestoneId
     * @return
     */
    List<Result> getMilestoneResultsByMilestoneId(long milestoneId) throws MilestoneNotFoundException;

    /**
     * 接包方上传成果
     *
     * @param taskId
     * @param result
     */
    void uploadResult(long taskId, Result result) throws MilestoneNotFoundException;

    /**
     * 接包方下载成果
     *
     * @param resultId
     */
    String downloadResult(long resultId) throws ResultNotFoundException;

    /**
     * 审核里程碑,pass or notpass，pass则将里程碑所在的任务和项目完成数加一
     *
     * @param milestoneId
     * @param status      pass or notpass
     */
    Milestone auditMilestoneByMilestoneId(long milestoneId, MilestoneStatus status) throws MilestoneNotFoundException;

    /**
     * 以数组的形式添加里程碑,添加之后将里程碑所在的任务和项目里程碑总个数更新
     *
     * @param milestones
     * @param taskId
     */
    List<Milestone> addMilestone(List<Milestone> milestones, long taskId) throws TaskNotFoundException;
}
