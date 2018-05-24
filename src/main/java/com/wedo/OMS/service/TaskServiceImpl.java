package com.wedo.OMS.service;

import com.wedo.OMS.entity.*;
import com.wedo.OMS.enums.CodeStatus;
import com.wedo.OMS.enums.UserTaskRole;
import com.wedo.OMS.enums.VerifyStatus;
import com.wedo.OMS.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        return taskRepository.findTasksByNameContaining(taskname);
    }

    @Override
    public Code addTask(Task task,long projectId) {
        Project project = projectRepository.findProjectById(projectId);
        task.setProject(project);
        taskRepository.save(task);
        String str = getRandomString(10);
        Code cod = new Code();
        cod.setCode(str);
        cod.setTask(task);
        cod.setStatus(CodeStatus.VALID);
        codeRepository.save(cod);
        return cod;
    }

    @Override
    public Task updateTaskByTaskId(long taskId, Task task) {
        Task newTask = taskRepository.findTaskById(taskId);
        newTask = task;
        taskRepository.save(newTask);
        return newTask;
    }

    @Override
    public UserTask addTaskUser(long taskId,long userId, UserTaskRole utr) {
        User user = userRepository.findUserById(userId);
        Task task = taskRepository.findTaskById(taskId);
        UserTask userTask = new UserTask();
        userTask.setUser(user);
        userTask.setTask(task);
        userTask.setStatus(VerifyStatus.ADD_CHECK);
        userTask.setUserTaskRole(utr);
        userTaskRepository.save(userTask);
        return userTask;
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
        code.setStatus(CodeStatus.INVALID);
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

    public static String getRandomString(int length){
        //定义一个字符串（A-Z，a-z，0-9）即62位；
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        //由Random生成随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //长度为几就循环几次
        for(int i=0; i<length; ++i){
            //产生0-61的数字
            int number=random.nextInt(62);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }

    /**
     * 查询该任务所有成员
     * @param taskId
     * @return
     */
    public List<User> findUsersByTaskId(long taskId){
        Task task = taskRepository.findTaskById(taskId);
        List<UserTask> userTasks = userTaskRepository.findUserTasksByTask(task);
        List<User> users = new ArrayList<>();
        for(UserTask userTask:userTasks){
            users.add(userRepository.findUserById(userTask.getUser().getId()));
        }
        return users;
    }
}
