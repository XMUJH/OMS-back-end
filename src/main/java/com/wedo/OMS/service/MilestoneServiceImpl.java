package com.wedo.OMS.service;

import com.wedo.OMS.entity.Milestone;
import com.wedo.OMS.entity.Project;
import com.wedo.OMS.entity.Result;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.enums.MilestoneStatus;
import com.wedo.OMS.repository.MilestoneRepository;
import com.wedo.OMS.repository.ProjectRepository;
import com.wedo.OMS.repository.ResultRepository;
import com.wedo.OMS.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MilestoneServiceImpl implements MilestoneService{

    private MilestoneRepository milestoneRepository;
    private TaskRepository taskRepository;
    private ResultRepository resultRepository;
    private ProjectRepository projectRepository;

    public MilestoneServiceImpl(MilestoneRepository milestoneRepository, TaskRepository taskRepository, ResultRepository resultRepository, ProjectRepository projectRepository) {
        this.milestoneRepository = milestoneRepository;
        this.taskRepository = taskRepository;
        this.resultRepository = resultRepository;
        this.projectRepository = projectRepository;
    }

    /**
     * 根据任务ID查看里程碑
     * @param taskId
     * @return
     */
    @Override
    public List<Milestone> listMilestonesByTaskId(Long taskId) {
        Task task = taskRepository.findAllById(taskId);
        List<Milestone> milestones = milestoneRepository.findAllByTask(task);
        return milestones;
    }

    /**
     * 查看里程碑详情
     * @param milestoneId
     * @return
     */
    @Override
    public Milestone getMilestoneByMilestoneId(Long milestoneId) {
        Milestone milestone = milestoneRepository.findAllById(milestoneId);
        return milestone;
    }

    /**
     * 查看里程碑成果
     * @param milestoneId
     * @return
     */
    @Override
    public List<Result> getMilestoneResultsByMilestoneId(Long milestoneId) {
        Milestone milestone = milestoneRepository.findAllById(milestoneId);
        List<Result> results = resultRepository.findAllByMilestone(milestone);
        return results;
    }

    /**
     * 接包方上传成果
     * @param milestoneId
     * @param result
     */
    @Override
    public void uploadResult(Long milestoneId, Result result) {
        Milestone milestone = milestoneRepository.findAllById(milestoneId);
        result.setMilestone(milestone);
        resultRepository.save(result);
    }

    /**
     * 接包方下载成果
     * @param resultId
     */
    @Override
    public void downloadResult(Long resultId) {

    }

    /**
     * 审核里程碑,pass or notpass，pass则将里程碑所在的任务和项目完成数加一
     * @param milestoneId
     * @param status pass or notpass
     */
    @Override
    public void auditMilestoneByMilestoneId(Long milestoneId, MilestoneStatus status) {
        Milestone milestone = milestoneRepository.findAllById(milestoneId);
        milestone.setStatus(status);
        milestoneRepository.save(milestone);
        if(status == MilestoneStatus.PASS){
            Task task = milestoneRepository.findAllById(milestoneId).getTask();
            task.setCompletion(task.getCompletion()+1);
            taskRepository.save(task);
            Project project = task.getProject();
            while (project != null){
                project.setCompletion(project.getCompletion()+1);
                projectRepository.save(project);
                project = project.getBelong();
            }
        }
    }

    /**
     * 以数组的形式添加里程碑,添加之后将里程碑所在的任务和项目里程碑总个数更新
     * @param milestones
     * @param taskId
     */
    @Override
    public void addMilestone(List<Milestone> milestones,Long taskId){
        Task task = taskRepository.findAllById(taskId);
        int num = milestones.size();
        for (Milestone milestone:milestones){
            milestone.setTask(task);
        }
        milestoneRepository.saveAll(milestones);
        task.setCompletion(task.getCompletion()+num);
        taskRepository.save(task);
        Project project = task.getProject();
        while (project != null){
            project.setCompletion(project.getCompletion()+num);
            projectRepository.save(project);
            project = project.getBelong();
        }
    }
}
