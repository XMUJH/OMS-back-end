package com.wedo.OMS.service;

import com.wedo.OMS.entity.Milestone;
import com.wedo.OMS.entity.Project;
import com.wedo.OMS.entity.Result;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.enums.MilestoneStatus;
import com.wedo.OMS.exception.MilestoneNotFoundException;
import com.wedo.OMS.exception.ResultNotFoundException;
import com.wedo.OMS.exception.TaskNotFoundException;
import com.wedo.OMS.repository.MilestoneRepository;
import com.wedo.OMS.repository.ProjectRepository;
import com.wedo.OMS.repository.ResultRepository;
import com.wedo.OMS.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MilestoneServiceImpl implements MilestoneService {

    private final MilestoneRepository milestoneRepository;
    private final TaskRepository taskRepository;
    private final ResultRepository resultRepository;
    private final ProjectRepository projectRepository;

    public MilestoneServiceImpl(MilestoneRepository milestoneRepository, TaskRepository taskRepository, ResultRepository resultRepository, ProjectRepository projectRepository) {
        this.milestoneRepository = milestoneRepository;
        this.taskRepository = taskRepository;
        this.resultRepository = resultRepository;
        this.projectRepository = projectRepository;
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
        return milestoneRepository.findAllByTask(task);
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
}
