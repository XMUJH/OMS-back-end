package com.wedo.OMS.service;

import com.wedo.OMS.entity.*;
import com.wedo.OMS.enums.MilestoneStatus;
import com.wedo.OMS.exception.MilestoneNotFoundException;
import com.wedo.OMS.exception.ResultNotFoundException;
import com.wedo.OMS.exception.TaskNotFoundException;
import com.wedo.OMS.repository.*;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class MilestoneServiceImpl implements MilestoneService {

    private final MilestoneRepository milestoneRepository;
    private final TaskRepository taskRepository;
    private final ResultRepository resultRepository;
    private final ProjectRepository projectRepository;
    private final MilestoneHistoryRepository milestoneHistoryRepository;

    public MilestoneServiceImpl(MilestoneRepository milestoneRepository, TaskRepository taskRepository, ResultRepository resultRepository, ProjectRepository projectRepository,final MilestoneHistoryRepository milestoneHistoryRepository) {
        this.milestoneRepository = milestoneRepository;
        this.taskRepository = taskRepository;
        this.resultRepository = resultRepository;
        this.projectRepository = projectRepository;
        this.milestoneHistoryRepository = milestoneHistoryRepository;
    }

    /**
     * 根据任务ID查看里程碑
     *
     * @param taskId
     * @return
     */
    @Override
    public List<Milestone> listMilestonesByTaskId(long taskId) throws TaskNotFoundException {
        Task task = taskRepository.findTaskById(taskId);
        if (task == null) {
            throw new TaskNotFoundException();
        }
        return milestoneRepository.findMilestonesByTask(task);
    }

    /**
     * 查看里程碑详情
     *
     * @param milestoneId
     * @return
     */
    @Override
    public Milestone getMilestoneByMilestoneId(long milestoneId) {
        return milestoneRepository.findMilestoneById(milestoneId);
    }

    /**
     * 查看里程碑成果
     *
     * @param milestoneId
     * @return
     */
    @Override
    public List<Result> getMilestoneResultsByMilestoneId(long milestoneId) throws MilestoneNotFoundException {
        Milestone milestone = milestoneRepository.findMilestoneById(milestoneId);
        if (milestone == null) {
            throw new MilestoneNotFoundException();
        }
        return resultRepository.findAllByMilestone(milestone);
    }

    /**
     * 接包方上传成果
     *
     * @param milestoneId
     * @param result
     */
    @Override
    public void uploadResult(long milestoneId, Result result) throws MilestoneNotFoundException {
        Milestone milestone = milestoneRepository.findMilestoneById(milestoneId);
        if (milestone == null) {
            throw new MilestoneNotFoundException();
        }
        result.setMilestone(milestone);
        resultRepository.save(result);
    }

    /**
     * 接包方下载成果
     *
     * @param resultId
     */
    @Override
    public String downloadResult(long resultId) throws ResultNotFoundException {
        Result result = resultRepository.findResultById(resultId);
        if (result == null) {
            throw new ResultNotFoundException();
        }
        return result.getAddress();
    }

    /**
     * 审核里程碑,pass or notpass，pass则将里程碑所在的任务和项目完成数加一
     *
     * @param milestoneId
     * @param status      pass or notpass
     */
    @Override
    public Milestone auditMilestoneByMilestoneId(long milestoneId, MilestoneStatus status) throws MilestoneNotFoundException {
        Milestone milestone = milestoneRepository.findMilestoneById(milestoneId);
        if (milestone == null) {
            throw new MilestoneNotFoundException();
        }
        milestone.setStatus(status);
        milestoneRepository.save(milestone);
        if (status == MilestoneStatus.PASS) {
            //TODO add null checl
            Task task = milestoneRepository.findMilestoneById(milestoneId).getTask();
            task.setCompletion(task.getCompletion() + 1);
            taskRepository.save(task);
            Project project = task.getProject();
            while (project != null) {
                project.setCompletion(project.getCompletion() + 1);
                projectRepository.save(project);
                project = project.getBelong();
            }
        }
        return milestone;
    }

    /**
     * 以数组的形式添加里程碑,添加之后将里程碑所在的任务和项目里程碑总个数更新
     *
     * @param milestones
     * @param taskId
     */
    @Override
    public List<Milestone> addMilestone(List<Milestone> milestones, long taskId) throws TaskNotFoundException {
        Task task = taskRepository.findTaskById(taskId);
        if (task == null) {
            throw new TaskNotFoundException();
        }
        int num = milestones.size();
        for (Milestone milestone : milestones) {
            milestone.setTask(task);
        }
        milestoneRepository.saveAll(milestones);
        task.setCompletion(task.getCompletion() + num);
        taskRepository.save(task);
        Project project = task.getProject();
        while (project != null) {
            project.setCompletion(project.getCompletion() + num);
            projectRepository.save(project);
            project = project.getBelong();
        }
        return milestones;
    }

    /**
     * 获取某里程碑全部成果审核情况
     * @param milestoneId
     * @return
     */
    @Override
    public List<MilestoneHistory> getMilestoneHistoriesByMilestoneId(long milestoneId){
        Milestone milestone = milestoneRepository.findMilestoneById(milestoneId);
        return milestoneHistoryRepository.findMilestoneHistoriesByMilestone(milestone);
    }

    /**
     * 添加审核不通过原因
     * @param milestoneHistoryId
     * @param reason
     * @return
     */
    @Override
    public MilestoneHistory setMilestoneHistoryReason(long milestoneHistoryId,String reason){
        MilestoneHistory milestoneHistory  = milestoneHistoryRepository.findMilestoneHistoryById(milestoneHistoryId);
        milestoneHistory.setReason(reason);
        milestoneHistoryRepository.save(milestoneHistory);
        return milestoneHistory;
    }

    /**
     * 获取里程碑当前审核记录
     * @return
     */
    @Override
    public MilestoneHistory getCurrentMilestoneHistory(long status,Milestone milestone){
        return milestoneHistoryRepository.findMilestoneHistoryByStatusAndMilestone(status,milestone);
    }

    /**
     * 获取某任务全部里程碑成果审核情况
     * @param taskId
     * @return
     */
    @Override
    public List<MilestoneHistory> getMilestoneHistoriesByTaskId(long taskId){
        Task task = taskRepository.findTaskById(taskId);
        List<Milestone> milestones = milestoneRepository.findMilestonesByTask(task);
        List<MilestoneHistory> milestoneHistories = new ArrayList<>();
        for(Milestone milestone:milestones){
            milestoneHistories.addAll(milestoneHistoryRepository.findMilestoneHistoriesByMilestone(milestone));
        }
        return milestoneHistories;
    }

    /**
     * 将里程碑审核记录按照时间排序
     * @param milestoneHistories
     * @return
     */
    @Override
    public List<MilestoneHistory> sortMilestoneHistoriesByTime(List<MilestoneHistory> milestoneHistories){
        for(int i=0;i<milestoneHistories.size()-1;i++){
            for(int j=i+1;j<milestoneHistories.size();j++){
                if(milestoneHistories.get(i).getCreateTime().compareTo(milestoneHistories.get(j).getCreateTime()) == 1){
                    MilestoneHistory tmp = milestoneHistories.get(i);
                    milestoneHistories.set(i,milestoneHistories.get(j));
                    milestoneHistories.set(j,tmp);
                }
            }
        }
        return milestoneHistories;
    }

    /**
     * 存一条新的审核记录
     * @param milestoneHistory
     * @return
     */
    @Override
    public MilestoneHistory saveNewMilestoneHistory(MilestoneHistory milestoneHistory){
        return milestoneHistoryRepository.save(milestoneHistory);
    }

    /**
     * 获得里程碑所有成果
     * @param milestoneId
     * @return
     */
    @Override
    public List<Result> getResultsByMilestone(long milestoneId){
        Milestone milestone = milestoneRepository.findMilestoneById(milestoneId);
        return resultRepository.findResultsByMilestone(milestone);
    }

    /**
     * 用户更新里程碑
     *
     * @param milestone
     * @param milestoneId
     * @return
     */
    @Override
    public void updateMilestone(Milestone milestone, long milestoneId){
        Milestone milestoneOrgin = milestoneRepository.findMilestoneById(milestoneId);
        if(milestone.getName()!=null) milestoneOrgin.setName(milestone.getName());
        if(milestone.getInfo()!=null) milestoneOrgin.setInfo(milestone.getInfo());
        if(milestone.getEndTime()!=null) milestoneOrgin.setEndTime(milestone.getEndTime());
        if(milestone.getStatus()!=null) milestoneOrgin.setStatus(milestone.getStatus());
        milestoneRepository.save(milestoneOrgin);
    }

    /**
     * 用户上传资源
     *
     * @param result
     * @param milestoneId
     * @return
     */
    @Override
    public void newResult(Result result, long milestoneId)
    {
        Milestone milestone=milestoneRepository.findMilestoneById(milestoneId);
        milestone.setSubmitTime(milestone.getSubmitTime()+1);
        milestone.setStatus(MilestoneStatus.NOTPASS);
        milestoneRepository.save(milestone);
        result.setMilestone(milestone);
        resultRepository.save(result);
        MilestoneHistory milestoneHistory=new MilestoneHistory();
        milestoneHistory.setStatus(0);
        milestoneHistory.setMilestone(milestone);
        milestoneHistory.setCreateTime(result.getCommit());
        milestoneHistoryRepository.save(milestoneHistory);
    }
}
