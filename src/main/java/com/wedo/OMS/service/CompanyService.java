package com.wedo.OMS.service;

import com.wedo.OMS.entity.Company;
import com.wedo.OMS.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {
    /**
     * 根据用户ID获取用户公司
     * @param userId
     * @return
     */
    Company getCompanyByUserId(Long userId);

    /**
     * 根据公司名搜索公司
     * @param
     * @return
     */
    List<Company> listCompaniesByCompanyname(String companyname);

    /**
     * 发包方根据公司ID获取公司的所有成员
     * @param companyId
     * @return
     */
    List<User> listCompanyUsersByCompanyId(Long companyId);

    /**
     * 队长根据名字搜索公司成员
     * @param username
     * @return
     */
    List<User> ListCompanyUsersByUsername(Long leaderid,String username);

    /**
     * 发包方新建公司
     * @param company
     * @return
     */
    Company addCompany(Company company);

    /**
     * 发包方删除公司
     * @param companyId
     */
    void deleteCompanyByCompanyId(Long companyId);

    /**
     * 删除公司成员
     * @param companyId
     * @param userId
     */
    void deleteCompanyUser(Long companyId, Long userId);


}
