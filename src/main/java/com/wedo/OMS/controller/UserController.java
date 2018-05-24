package com.wedo.OMS.controller;


import com.arcsoft.service.AFRService;
import com.wedo.OMS.entity.*;
import com.wedo.OMS.enums.MilestoneStatus;
import com.wedo.OMS.exception.UserNotFoundException;
import com.wedo.OMS.repository.MilestoneRepository;
import com.wedo.OMS.repository.ProjectRepository;
import com.wedo.OMS.repository.TaskRepository;
import com.wedo.OMS.repository.UserRepository;
import com.wedo.OMS.service.MilestoneService;
import com.wedo.OMS.service.UserService;
import com.wedo.OMS.utils.FileUtil;
import com.wedo.OMS.viewmodel.UserViewModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class UserController {
    private UserService userService;
    private UserRepository userRepository;
    private MilestoneRepository milestoneRepository;
    private TaskRepository taskRepository;
    private MilestoneService milestoneService;
    private ProjectRepository projectRepository;
    private AFRService afrService;

    public UserController(AFRService afrService, UserService userService, ProjectRepository projectRepository, MilestoneService milestoneService, UserRepository userRepository, MilestoneRepository milestoneRepository, TaskRepository taskRepository) {
        this.afrService = afrService;
        this.userService = userService;
        this.projectRepository = projectRepository;
        this.milestoneService = milestoneService;
        this.userRepository = userRepository;
        this.milestoneRepository = milestoneRepository;
        this.taskRepository = taskRepository;
    }

    @PostMapping(value = "/login")
    public UserViewModel login(@RequestBody User user) throws NoSuchAlgorithmException, UnsupportedEncodingException, UserNotFoundException {
        user = userService.login(user);
        if (user == null) {
            throw new UserNotFoundException();
        } else
            return new UserViewModel(user);
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
    @PostMapping(value = "/facerecognition")
    public @ResponseBody
    int faceRecogintion(@RequestParam("img") MultipartFile file, HttpServletRequest request) {
        String contentType = file.getContentType();
        System.out.println(contentType);
        String fileName = file.getOriginalFilename() + ".png";
        System.out.println(fileName);
        String filePath = "src/main/resources/static/faceTemp/";
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }
        String result = afrService.doFR("src/main/resources/static/faceTemp/" + fileName, new String[]{"src/main/resources/static/photo/photo-hjh.jpg", "src/main/resources/static/faceimg/002.jpg", "src/main/resources/static/faceimg/003.jpg"}).toString();
        if (result == "Warning! Third Party Faces Detected") {
            System.out.println("Warning! Third Party Faces Detected");
        }
        if (result == "Recognition Successful!") return 1;
        if (result == "Recognition Failed!") return 2;
        else return -1;
    }
}
