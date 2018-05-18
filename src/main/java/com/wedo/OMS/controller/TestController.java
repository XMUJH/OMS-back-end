package com.wedo.OMS.controller;

import com.wedo.OMS.entity.Attendance;
import com.wedo.OMS.entity.Company;
import com.wedo.OMS.entity.Record;
import com.wedo.OMS.entity.User;
import com.wedo.OMS.repository.CompanyRepository;
import com.wedo.OMS.repository.RecordRepository;
import com.wedo.OMS.service.AttendanceService;
import com.wedo.OMS.service.CompanyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    private AttendanceService attendanceService;
    private CompanyService companyService;
    private RecordRepository recordRepository;

    public TestController(AttendanceService attendanceService, RecordRepository recordRepository, CompanyService companyService) {
       this.attendanceService = attendanceService;
       this.recordRepository = recordRepository;
       this.companyService = companyService;
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
    @GetMapping(value = "/userCompany")
    public Company getCompanyByUserId() {
        long userid = 1;
        return companyService.getCompanyByUserId(userid);
    }

    @GetMapping(value = "/companies")
    public List<Company> listCompanysByCompanyname() {
        return companyService.listCompaniesByCompanyname("a");
    }

    @GetMapping(value = "/companyMembers")
    public  List<User> listCompanyUsersByCompanyId() {
        long companyid=1;
        return companyService.listCompanyUsersByCompanyId(companyid);
    }

    @GetMapping(value = "/selectMember")
    public  List<User> ListCompanyUsersByUsername() {
        return companyService.ListCompanyUsersByUsername("a");
    }

    @GetMapping(value = "/newCompany")
    public Company addCompany() {
        Company company = new Company();
        long id =1;
        company.setId(id);
        company.setName("Wedo");
        return companyService.addCompany(company);
    }
}
