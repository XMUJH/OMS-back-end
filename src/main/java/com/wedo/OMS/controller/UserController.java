package com.wedo.OMS.controller;


import com.arcsoft.service.AFRService;
import com.wedo.OMS.entity.*;
import com.wedo.OMS.enums.MilestoneStatus;
import com.wedo.OMS.exception.MilestoneNotFoundException;
import com.wedo.OMS.exception.PasswordIncorrectException;
import com.wedo.OMS.exception.UserNotFoundException;
import com.wedo.OMS.repository.MilestoneRepository;
import com.wedo.OMS.repository.ProjectRepository;
import com.wedo.OMS.repository.TaskRepository;
import com.wedo.OMS.service.MilestoneService;
import com.wedo.OMS.service.UserService;
import com.wedo.OMS.utils.FileUtil;
import com.wedo.OMS.viewmodel.UserViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final MilestoneRepository milestoneRepository;
    private final TaskRepository taskRepository;
    private final MilestoneService milestoneService;
    private final ProjectRepository projectRepository;
    private final AFRService afrService;

    public UserController(AFRService afrService, UserService userService, ProjectRepository projectRepository, MilestoneService milestoneService, MilestoneRepository milestoneRepository, TaskRepository taskRepository) {
        this.afrService = afrService;
        this.userService = userService;
        this.projectRepository = projectRepository;
        this.milestoneService = milestoneService;
        this.milestoneRepository = milestoneRepository;
        this.taskRepository = taskRepository;
    }

    @PostMapping(value = "/login")
    public UserViewModel login(@RequestBody User user) throws UserNotFoundException, PasswordIncorrectException {
        User userFound = userService.login(user);
        return new UserViewModel(userFound);
    }

    @GetMapping(value = "/test")
    public void test() {
        Task task = new Task();
        Project project = new Project();
        project.setName("project");
        task.setProject(project);
        task.setName("task");
        projectRepository.save(project);
        taskRepository.save(task);
    }

    @GetMapping(value = "/user")
    public List<Result> getUserInfo() throws MilestoneNotFoundException {
        long a = 1;
        MilestoneStatus b = MilestoneStatus.NOTBEGIN;
        milestoneService.auditMilestoneByMilestoneId(a, b);
        return milestoneService.getMilestoneResultsByMilestoneId(a);
    }

    @GetMapping(value = "/milestone")
    public List<Milestone> getMilestoneInfo() {
        Milestone milestone = new Milestone();
        Task task = new Task();
        task.setInfo("1231231");
        taskRepository.save(task);
        milestone.setTask(task);

        milestoneRepository.save(milestone);
        return milestoneRepository.findAll();
    }

    //TODO Should be removed
    @PostMapping(value = "/faceRecognition")
    public @ResponseBody
    int faceRecognition(@RequestParam("img") MultipartFile file) {
        String contentType = file.getContentType();
        System.out.println(contentType);
        String fileName = file.getOriginalFilename() + ".png";
        System.out.println(fileName);
        String filePath = "src/main/resources/static/faceTemp/";
        try {
            FileUtil.write(file.getBytes(), filePath, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return -1;
        }
        String result = afrService.doFR("src/main/resources/static/faceTemp/" + fileName, new String[]{"src/main/resources/static/photo/photo-hjh.jpg", "src/main/resources/static/faceimg/002.jpg", "src/main/resources/static/faceimg/003.jpg"});
        if (result.equals("Warning! Third Party Faces Detected")) {
            System.out.println("Warning! Third Party Faces Detected");
        }
        if (result.equals("Recognition Successful!")) return 1;
        if (result.equals("Recognition Failed!")) return 2;
        else return -1;
    }
}
