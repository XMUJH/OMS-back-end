package com.wedo.OMS.controller;

import com.wedo.OMS.entity.Milestone;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.entity.User;
import com.wedo.OMS.repository.MilestoneRepository;
import com.wedo.OMS.repository.TaskRepository;
import com.wedo.OMS.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private UserRepository userRepository;
    private MilestoneRepository milestoneRepository;
    private TaskRepository taskRepository;

    public UserController(UserRepository userRepository, MilestoneRepository milestoneRepository, TaskRepository taskRepository) {

        this.userRepository = userRepository;
        this.milestoneRepository = milestoneRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping(value = "/user")
    public List<User> getUserInfo() {
        User user = new User();
        user.setName("123");
        userRepository.save(user);
        return userRepository.findAll();
    }

    @GetMapping(value = "/milestone")
    public List<Milestone> getMilestoneInfo() {
        Milestone milestone = new Milestone();
        Task task = new Task();
        task.setInfo("1231231");
        taskRepository.save(task);
        milestone.setChangeCount(2);
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
