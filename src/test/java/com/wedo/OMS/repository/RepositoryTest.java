package com.wedo.OMS.repository;

import com.wedo.OMS.entity.Project;
import com.wedo.OMS.entity.User;
import com.wedo.OMS.enums.Gender;
import com.wedo.OMS.enums.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private CodeRepository CodeRepository;
    @Autowired
    private CompanyRepository CompanyRepository;
    @Autowired
    private MilestoneRepository milestoneRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTaskRepository userTaskRepository;


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void initMySQL() {
        //初始化Company
        //初始化User
        /*
        User user0 = new User("2432015000", "18859272730", "609490911@qq.com", "邓荟丹", "000", Gender.FAMALE, null, UserRole.RECEIVER, null, null);
        User user1 = new User("2432015001", "18859272731", "609490912@qq.com", "邓帅", "001", Gender.MALE, null, UserRole.RECEIVER, null, null);
        User user2 = new User("2432015002", "18859272732", "609490913@qq.com", "张渝萍", "002", Gender.FAMALE, null, UserRole.RECEIVER, null, null);
        User user3 = new User("2432015003", "18859272733", "609490914@qq.com", "胡江海", "003", Gender.MALE, null, UserRole.RECEIVER, null, null);
        User user4 = new User("2432015004", "18859272734", "609490915@qq.com", "陈童", "004", Gender.FAMALE, null, UserRole.RECEIVER, null, null);
        User user5 = new User("2432015005", "18859272735", "609490916@qq.com", "李恒贵", "005", Gender.MALE, null, UserRole.RECEIVER, null, null);
        User user6 = new User("2432015006", "18859272736", "609490917@qq.com", "管理员", "006", Gender.MALE, null, UserRole.SENDER, null, null);
        userRepository.save(user0);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);

        //初始化项目
        Project project0 = new Project("项目一", "000", null, new Date(2018, 3, 5, 12, 0, 0), new Date(2018, 3, 5, 12, 0, 0), new Date(2018, 4, 5, 12, 0, 0), 0, 0);
        Project project1 = new Project("项目二", "001", null, new Date(2018, 3, 5, 12, 0, 0), new Date(2018, 3, 5, 12, 0, 0), new Date(2018, 4, 5, 12, 0, 0), 0, 0);
        projectRepository.save(project0);
        projectRepository.save(project1);
        */

        /*//初始化Task
        Task task0= new Task("任务一",project0,new Date(2018,3,5,12,0,0),new Date(2018,3,5,12,0,0),new Date(2018,3,5,12,0,0),SafetyLevel.A,"项目一描述",null,null,0,3,0);
        Task task1= new Task("任务二",project0,new Date(2018,3,5,12,0,0),new Date(2018,3,5,12,0,0),new Date(2018,3,5,12,0,0),SafetyLevel.A,"项目二描述",null,null,0,3,0);
        taskRepository.save(task0);
        taskRepository.save(task1);

        //初始化milestone
        Milestone milestone0=new Milestone("里程碑一",task0,new Date(2018,3,5,12,0,0),new Date(2018,3,5,12,0,0),"里程碑一详情描述",MilestoneStatus.NOTAUDIT);
        Milestone milestone1=new Milestone("里程碑二",task0,new Date(2018,3,5,12,0,0),new Date(2018,3,5,12,0,0),"里程碑二详情描述",MilestoneStatus.NOTAUDIT);
        Milestone milestone2=new Milestone("里程碑三",task0,new Date(2018,3,5,12,0,0),new Date(2018,3,5,12,0,0),"里程碑三详情描述",MilestoneStatus.NOTAUDIT);
        Milestone milestone3=new Milestone("里程碑四",task1,new Date(2018,3,5,12,0,0),new Date(2018,3,5,12,0,0),"里程碑四详情描述",MilestoneStatus.NOTAUDIT);
        Milestone milestone4=new Milestone("里程碑五",task1,new Date(2018,3,5,12,0,0),new Date(2018,3,5,12,0,0),"里程碑五详情描述",MilestoneStatus.NOTAUDIT);
        Milestone milestone5=new Milestone("里程碑六",task1,new Date(2018,3,5,12,0,0),new Date(2018,3,5,12,0,0),"里程碑六详情描述",MilestoneStatus.NOTAUDIT);
    */
    }
}