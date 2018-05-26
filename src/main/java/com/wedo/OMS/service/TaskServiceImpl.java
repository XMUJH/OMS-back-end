package com.wedo.OMS.service;

import com.wedo.OMS.entity.*;
import com.wedo.OMS.enums.CodeStatus;
import com.wedo.OMS.enums.UserTaskRole;
import com.wedo.OMS.enums.VerifyStatus;
import com.wedo.OMS.exception.*;
import com.wedo.OMS.repository.*;
import com.wedo.OMS.utils.CodeUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserTaskRepository userTaskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final CodeRepository codeRepository;
    private final RecordRepository recordRepository;

    TaskServiceImpl(TaskRepository taskRepository, UserTaskRepository userTaskRepository, UserRepository userRepository, ProjectRepository projectRepository, CodeRepository codeRepository, RecordRepository recordRepository) {
        this.taskRepository = taskRepository;
        this.userTaskRepository = userTaskRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.codeRepository = codeRepository;
        this.recordRepository = recordRepository;
    }

    @Override
    public List<Task> listTasksByUserId(long userId) throws UserNotFoundException {
        User user = userRepository.findUserById(userId);
        nullCheck(user);
        List<UserTask> userTasks = userTaskRepository.findUserTasksByUser(user);
        List<Task> tasks = new ArrayList<>();
        for (UserTask ut : userTasks) {
            if (ut == null) {
                continue;
            }
            tasks.add(ut.getTask());
        }
        return tasks;
    }

    @Override
    public Task getTaskByTaskId(long taskId) throws TaskNotFoundException {
        Task task = taskRepository.findTaskById(taskId);
        if (task == null) {
            throw new TaskNotFoundException();
        }
        return task;
    }

    @Override
    public List<Task> getTasksByTaskName(String taskname) {
        return taskRepository.findTasksByNameContaining(taskname);
    }

    @Override
    public Code addTask(Task task, long projectId) throws ProjectNotFoundException {
        Project project = projectRepository.findProjectById(projectId);
        if (project == null) {
            throw new ProjectNotFoundException();
        }
        task.setProject(project);
        taskRepository.save(task);
        // Generate Activation Code
        String str = CodeUtils.getRandomString(10);
        Code code = new Code();
        code.setCode(str);
        code.setTask(task);
        code.setStatus(CodeStatus.VALID);
        codeRepository.save(code);
        return code;
    }

    @Override
    public Task updateTaskByTaskId(long taskId, Task task) {
        task.setId(taskId);
        taskRepository.save(task);
        return task;
    }

    @Override
    public UserTask addTaskUser(long taskId, long userId, UserTaskRole userTaskRole) throws UserNotFoundException, TaskNotFoundException {
        User user = userRepository.findUserById(userId);
        Task task = taskRepository.findTaskById(taskId);
        nullCheck(user, task);
        UserTask userTask = new UserTask();
        userTask.setUser(user);
        userTask.setTask(task);
        userTask.setStatus(VerifyStatus.ADD_CHECK);
        userTask.setUserTaskRole(userTaskRole);
        userTaskRepository.save(userTask);
        return userTask;
    }

    @Override
    public UserTask auditTaskUserById(long userId, long taskId, VerifyStatus status) throws UserNotFoundException, TaskNotFoundException {
        User user = userRepository.findUserById(userId);
        Task task = taskRepository.findTaskById(taskId);
        nullCheck(user, task);
        UserTask userTask = userTaskRepository.findUserTasksByUserAndTask(user, task);
        userTask.setStatus(status);
        userTask.setTask(task);
        userTaskRepository.save(userTask);
        return userTask;
    }

    @Override
    public void deleteTaskUserById(long userId, long taskId) throws UserNotFoundException, TaskNotFoundException {
        User user = userRepository.findUserById(userId);
        Task task = taskRepository.findTaskById(taskId);
        nullCheck(user, task);
        userTaskRepository.deleteByUserAndTask(user, task);
    }

    public List<Task> findTasks(long userId, UserTaskRole userTaskRole) throws UserNotFoundException {
        User user = userRepository.findUserById(userId);
        nullCheck(user);
        List<UserTask> userTasks = userTaskRepository.findUserTasksByUserAndUserTaskRole(user, userTaskRole);
        List<Task> tasks = new ArrayList<>();
        for (UserTask userTask : userTasks) {
            tasks.add(taskRepository.findTaskById(userTask.getTask().getId()));
        }
        return tasks;
    }

    @Override
    public Code findCodeByCode(String origin) throws CodeNotFoundException {
        Code code = codeRepository.findCodeByCode(origin);
        if (code == null) {
            throw new CodeNotFoundException();
        }
        return code;
    }

    @Override
    public Code updateCodeStatus(Code code) {
        code.setStatus(CodeStatus.INVALID);
        codeRepository.save(code);
        return code;
    }

    @Override
    public UserTask addUserTask(Code code, long userId) throws UserNotFoundException, TaskNotFoundException {
        UserTask userTask = new UserTask();
        Task task = taskRepository.findTaskById(code.getTask().getId());
        User user = userRepository.findUserById(userId);
        nullCheck(user, task);
        userTask.setStatus(VerifyStatus.NORMAL);
        userTask.setTask(task);
        userTask.setUser(user);
        userTask.setUserTaskRole(UserTaskRole.LEADER);
        userTaskRepository.save(userTask);
        return userTask;
    }
    @Override
    public List<User> findUsersByTaskId(long taskId) throws TaskNotFoundException {
        Task task = taskRepository.findTaskById(taskId);
        if (task == null) {
            throw new TaskNotFoundException();
        }
        List<UserTask> userTasks = userTaskRepository.findUserTasksByTask(task);
        List<User> users = new ArrayList<>();
        for (UserTask userTask : userTasks) {
            users.add(userRepository.findUserById(userTask.getUser().getId()));
        }
        return users;
    }

    @Override
    public List<Record> findAllRecord() throws RecordNotFoundException{
        return recordRepository.findAll();
    }
    private void nullCheck(User user) throws UserNotFoundException {
        if (user == null) {
            throw new UserNotFoundException();
        }
    }

    private void nullCheck(User user, Task task) throws UserNotFoundException, TaskNotFoundException {
        nullCheck(user);
        if (task == null) {
            throw new TaskNotFoundException();
        }
    }
}
