package com.wedo.OMS.controller;

import com.wedo.OMS.entity.Milestone;
import com.wedo.OMS.entity.Result;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.enums.MilestoneStatus;
import com.wedo.OMS.repository.MilestoneRepository;
import com.wedo.OMS.repository.TaskRepository;
import com.wedo.OMS.repository.UserRepository;
import com.wedo.OMS.service.MilestoneService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private UserRepository userRepository;
    private MilestoneRepository milestoneRepository;
    private TaskRepository taskRepository;
    private MilestoneService milestoneService;

    public UserController(MilestoneService milestoneService, UserRepository userRepository, MilestoneRepository milestoneRepository, TaskRepository taskRepository) {
        this.milestoneService = milestoneService;
        this.userRepository = userRepository;
        this.milestoneRepository = milestoneRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping(value = "/user")
    public List<Result> getUserInfo() {
        //User user = new User();
        //user.setName("123");
        //userRepository.save(user);
        //Result result = new Result();
        //result.setName("第二个成果");
        long a = 1;
        MilestoneStatus b = MilestoneStatus.NOTBEGIN;
        //milestoneService.uploadResult(a, result);
        milestoneService.auditMilestoneByMilestoneId(a,b);
        return milestoneService.getMilestoneResultsByMilestoneId(a);
    }

    @GetMapping(value = "/milestone")
    public List<Milestone> getMilestoneInfo() {
        Milestone milestone = new Milestone();
        Task task = new Task();
        task.setInfo("1231231");
        taskRepository.save(task);
        //milestone.setChangeCount(2);
        milestone.setTask(task);

        milestoneRepository.save(milestone);
        return milestoneRepository.findAll();
    }

    /*@DeleteMapping(value = "/user")
    public boolean deleteUser(long userId) {
        userRepository.delete();
        userRepository.
    }*/
}
