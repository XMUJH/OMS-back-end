package com.wedo.OMS.service;

import com.wedo.OMS.entity.*;
import com.wedo.OMS.enums.CodeStatus;
import com.wedo.OMS.enums.UserTaskRole;
import com.wedo.OMS.enums.VerifyStatus;
import com.wedo.OMS.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private UserTaskRepository userTaskRepository;
    private UserRepository userRepository;
    private ProjectRepository projectRepository;
    private CodeRepository codeRepository;

    TaskServiceImpl(TaskRepository taskRepository, UserTaskRepository userTaskRepository, UserRepository userRepository,ProjectRepository projectRepository,CodeRepository codeRepository) {
        this.taskRepository = taskRepository;
        this.userTaskRepository = userTaskRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.codeRepository= codeRepository;
    }

    @Override
    public List<Task> listTasksByUserId(long userId) {
        User user = userRepository.findUserById(userId);
        List<UserTask> userTasks = userTaskRepository.findUserTasksByUser(user);
        List<Task> tasks = new ArrayList<>();
        for (UserTask ut : userTasks) {
            tasks.add(ut.getTask());
        }
        return tasks;
    }

    @Override
    public Task getTaskByTaskId(long taskId) {
        return taskRepository.findTaskById(taskId);
    }

    @Override
    public List<Task> getTasksByTaskname(String taskname) {
        return taskRepository.findTasksByName(taskname);
    }

    @Override
    public Task addTask(Task task,long projectId) {
        Project project = projectRepository.findProjectById(projectId);
        task.setProject(project);
        taskRepository.save(task);
        return task;
    }

    @Override
    public Task updateTaskByTaskId(long taskId, Task task) {
        Task newTask = taskRepository.findTaskById(taskId);
        newTask = task;
        taskRepository.save(newTask);
        return newTask;
    }

    @Override
    public void addTaskUser(long userId, UserTaskRole utr) {
        User user = userRepository.findUserById(userId);
        UserTask userTask = new UserTask();
        userTask.setUser(user);
        userTask.setStatus(VerifyStatus.ADD_CHECK);
        userTask.setUserTaskRole(utr);


    }

    @Override
    public UserTask auditTaskUserById(long userId, long taskId, VerifyStatus status) {
        User user = userRepository.findUserById(userId);
        Task task = taskRepository.findTaskById(taskId);
        UserTask userTask = userTaskRepository.findUserTasksByUserAndTask(user, task);
        userTask.setStatus(status);
        userTask.setTask(task);
        userTaskRepository.save(userTask);
        return userTask;
    }

    @Override
    public void deleteTaskUserById(long userId, long taskId) {
        User user = userRepository.findUserById(userId);
        Task task = taskRepository.findTaskById(taskId);
        userTaskRepository.deleteByUserAndTask(user, task);
    }

    /**
     * 根据人员身份查找该身份全部任务
     * @param userId
     * @param userTaskRole
     * @return
     */
    public List<Task> findTasks(long userId,UserTaskRole userTaskRole){
        User user= userRepository.findUserById(userId);
        List<UserTask> userTasks = userTaskRepository.findUserTasksByUserAndUserTaskRole(user,userTaskRole);
        List<Task> tasks = new ArrayList<>();
        for (UserTask userTask:userTasks){
            tasks.add(taskRepository.findTaskById(userTask.getTask().getId()));
        }
        return tasks;
    }

    /**
     * 根据激活码找到该code
     * @param code
     * @return
     */
    @Override
    public Code findCodeByCode(String code){
        return codeRepository.findCodeByCode(code);
    }

    /**
     * 任务激活后更改任务状态
     * @return
     */
    @Override
    public Code updateCodeStatus(Code code){
        code.setStatus(CodeStatus.VALID);
        codeRepository.save(code);
        return code;
    }

    /**
     * 任务激活后添加userTask负责人信息
     * @param code
     * @param userId
     * @return
     */
    @Override
    public UserTask addUserTask(Code code,long userId){
        UserTask userTask = new UserTask();
        Task task = taskRepository.findTaskById(code.getTask().getId());
        User user = userRepository.findUserById(userId);
        userTask.setStatus(VerifyStatus.NORMAL);
        userTask.setTask(task);
        userTask.setUser(user);
        userTask.setUserTaskRole(UserTaskRole.LEADER);
        userTaskRepository.save(userTask);
        return userTask;
    }
}
