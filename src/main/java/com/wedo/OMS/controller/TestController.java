package com.wedo.OMS.controller;

import com.arcsoft.service.AFRService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.ComponentScan;
import com.wedo.OMS.entity.Attendance;
import com.wedo.OMS.entity.Company;
import com.wedo.OMS.entity.Record;
import com.wedo.OMS.entity.User;
import com.wedo.OMS.entity.Resource;
import com.wedo.OMS.entity.Task;
import com.wedo.OMS.repository.CompanyRepository;
import com.wedo.OMS.repository.RecordRepository;
import com.wedo.OMS.repository.TaskRepository;
import com.wedo.OMS.service.AttendanceService;
import com.wedo.OMS.service.CompanyService;
import com.wedo.OMS.service.ResourceService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@ComponentScan(value="com.arcsoft")
public class TestController {
    private AttendanceService attendanceService;
    private CompanyService companyService;
    private RecordRepository recordRepository;
    private AFRService afrService;
    private ResourceService resourceService;
    private TaskRepository taskRepository;

    public TestController(AttendanceService attendanceService, RecordRepository recordRepository, CompanyService companyService, AFRService afrService, ResourceService resourceService, TaskRepository taskRepository) {
       this.attendanceService = attendanceService;
       this.recordRepository = recordRepository;
       this.companyService = companyService;
       this.afrService = afrService;
       this.resourceService=resourceService;
       this.taskRepository=taskRepository;
    }

    /*AttendanceService*/
    @GetMapping(value = "/userAttendances")
    public List<Attendance> getAttendancesByUserId() {
        long userid = 1;
        return attendanceService.getAttendancesByUserId(userid);
    }

    @GetMapping(value = "/memberAttendances")
    public List<Attendance> getTaskAttendancesByTaskId() {
        long taskid = 1;
        return attendanceService.getAttendancesByUserId(taskid);
    }

    @GetMapping(value = "/attendanceRecord")
    public Record getAttendanceRecordByRecordId() {
        long recordId = 1;
        return recordRepository.findRecordById(recordId);
    }
    /*CompanyService*/
    /**
     * 根据用户ID获取用户公司
     */
    @GetMapping(value = "/userCompany")
    public Company getCompanyByUserId() {
        long userid = 1;
        return companyService.getCompanyByUserId(userid);
    }

    /**
     * 根据公司名搜索公司
     */
    @GetMapping(value = "/companies")
    public List<Company> listCompanysByCompanyname() {
        return companyService.listCompaniesByCompanyname("a");
    }

    /**
     * 发包方根据公司ID获取公司的所有成员
     */
    @GetMapping(value = "/companyMembers")
    public  List<User> listCompanyUsersByCompanyId() {
        long companyid=1;
        return companyService.listCompanyUsersByCompanyId(companyid);
    }

    /**
     * 队长根据名字搜索公司成员
     */
    @GetMapping(value = "/selectMember")
    public  List<User> ListCompanyUsersByUsername() {
        return companyService.ListCompanyUsersByUsername("a");
    }

    /**
     * 发包方新建公司
     */
    @GetMapping(value = "/newCompany")
    public Company addCompany() {
        Company company = new Company();
        long id =1;
        company.setId(id);
        company.setName("Wedo");
        return companyService.addCompany(company);
    }

    /**
     * 发包方删除公司
     */
    @GetMapping(value = "/deleteCompany")
    public void deleteCompanyByCompanyId() {
        long id =1;
        companyService.deleteCompanyByCompanyId(id);
    }

    /**
     * 删除公司成员
     */
    @GetMapping(value = "/deleteCompanyMember")
    public void deleteCompanyUser() {
        long companyid=1;
        long userid=1;
        companyService.deleteCompanyUser(companyid,userid);
    }

    @GetMapping(value = "/facetest")
    public String faceTest() {
        return afrService.doFR("src/main/resources/static/004.png", new String[]{"src/main/resources/static/faceimg/001.jpg", "src/main/resources/static/faceimg/002.jpg", "src/main/resources/static/faceimg/003.jpg"});
    }
}
