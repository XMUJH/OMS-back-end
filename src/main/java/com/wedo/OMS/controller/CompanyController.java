package com.wedo.OMS.controller;

import com.wedo.OMS.entity.User;
import com.wedo.OMS.service.CompanyService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    /**
     * 管理员删除外包公司
     *
     * @param companyId
     */
    @DeleteMapping(value = "/companies/:companyId")
    public void deleteCompanyByCompanyId(@PathVariable("companyId") long companyId) {
        companyService.deleteCompanyByCompanyId(companyId);
    }

    /**
     * 发包方根据公司id获取公司所有成员
     *
     * @param companyId
     * @return
     */
    @GetMapping(value = "/companies/:companyId")
    public List<User> listCompanyUsersByCompanyId(@PathVariable("companyId") long companyId) {
        return companyService.listCompanyUsersByCompanyId(companyId);
    }

    /**
     * 发包方根据userId删除某公司成员
     *
     * @param userId
     */
    @DeleteMapping(value = "/users/:userId")
    public void deleteCompanyUser(@PathVariable("userId") long userId) {
        companyService.deleteCompanyUser(userId);
    }

}
