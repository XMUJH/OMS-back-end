package com.wedo.OMS.controller;


import com.arcsoft.service.AFRService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wedo.OMS.entity.Milestone;
import com.wedo.OMS.entity.Project;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.entity.User;
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

    @GetMapping(value = "/user/{userId}")
    public @ResponseBody
    User getUserInfo(@PathVariable("userId") long userId) throws UserNotFoundException {
        User user = userService.getUserByUserId(userId);
        return user;
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

    @PostMapping(value = "/faceRecognition/{userId}")
    public @ResponseBody
    ObjectNode faceRecognition(@RequestParam("img") MultipartFile file, @PathVariable("userId") long userId) throws UserNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode returnMessage = mapper.createObjectNode();
        String contentType = file.getContentType();
        System.out.println(contentType);
        String fileName = file.getOriginalFilename() + userId + ".jpg";
        System.out.println(fileName);
        String filePath = "src/main/resources/static/faceTemp/";
        String url = userService.getUserFaceUrlById(userId);
        User user = userService.getUserByUserId(userId);
        System.out.println(user.getRole().toString());
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }
        try {
            String result = afrService.doFR("src/main/resources/static/faceTemp/" + fileName, new String[]{"src/main/resources/static/faceimg/" + url});
            if (result.equals("Warning! Third Party Faces Detected")) {
                System.out.println("Warning! Third Party Faces Detected");
                returnMessage.put("success", false);
            }
            if (result.equals("Recognition Successful!")) {
                System.out.println("Recognition Successful!");
                returnMessage.put("success", true);
                returnMessage.put("userId", userId);
                returnMessage.put("userRole", user.getRole().toString());
            }
            if (result.equals("Recognition Failed!")) {
                System.out.println("Recognition Failed!");
                returnMessage.put("success", false);
            }
        } catch (Exception e) {

        } finally {
            return returnMessage;
        }
    }
}
