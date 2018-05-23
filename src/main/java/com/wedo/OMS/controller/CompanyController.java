package com.wedo.OMS.controller;

import com.wedo.OMS.entity.Company;
import com.wedo.OMS.entity.User;
import com.wedo.OMS.service.CompanyService;
import com.wedo.OMS.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {
    private CompanyService companyService;
    private UserService userService;
    public CompanyController(CompanyService companyService,UserService userService){
        this.companyService = companyService;
        this.userService = userService;
    }

    /**
     * 管理员删除外包公司
     * @param companyId
     */
    @DeleteMapping(value = "/companies/:id")
    public void deleteCompanyByCompanyId(@PathVariable("companyId") long companyId){
        companyService.deleteCompanyByCompanyId(companyId);
    }

    /**
     * 发包方根据公司id获取公司所有成员
     * @param companyId
     * @return
     */
    @GetMapping(value = "/companies/:id")
    public List<User> listCompanyUsersByCompanyId(@PathVariable("companyId") long companyId){
        return companyService.listCompanyUsersByCompanyId(companyId);
    }

    /**
     * 发包方根据userId删除某公司成员
     * @param userId
     */
    @DeleteMapping(value = "/users/:id")
    public void deleteCompanyUser(@PathVariable("userId") long userId){
        companyService.deleteCompanyUser(userId);
    }

}
