package com.wedo.OMS.controller;

import com.wedo.OMS.entity.Company;
import com.wedo.OMS.entity.User;
import com.wedo.OMS.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {
    private CompanyService companyService;
    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    /**
     * 管理员删除外包公司
     * @param companyId
     */
    @DeleteMapping(value = "/companies/:id")
    public void deleteCompanyByCompanyId(@PathVariable("companyId") Long companyId){
        companyService.deleteCompanyByCompanyId(companyId);
    }

    @GetMapping(value = "/companies/:id")
    public List<User> listCompanyUsersByCompanyId(@PathVariable("companyId") Long companyId){
        return companyService.listCompanyUsersByCompanyId(companyId);
    }
}
