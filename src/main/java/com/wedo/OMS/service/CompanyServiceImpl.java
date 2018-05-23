package com.wedo.OMS.service;

import com.wedo.OMS.entity.Company;
import com.wedo.OMS.entity.User;
import com.wedo.OMS.repository.CompanyRepository;
import com.wedo.OMS.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private UserRepository userRepository;
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(UserRepository userRepository,CompanyRepository companyRepository){
        this.userRepository = userRepository;
        this.companyRepository =companyRepository;
    }

    /**
     * 根据用户ID获取用户公司
     * @param userId
     * @return
     */
    @Override
    public Company getCompanyByUserId(long userId) {
        User user = userRepository.findUserById(userId);
        return companyRepository.findCompanyById(user.getCompany().getId());
    }

    /**
     * 根据公司名搜索公司
     * @param
     * @return
     */
    @Override
    public List<Company> listCompaniesByCompanyname(String companyname) {
        return companyRepository.findCompaniesByNameContaining(companyname);
    }

    /**
     * 发包方根据公司ID获取公司的所有成员
     * @param companyId
     * @return
     */
    @Override
    public List<User> listCompanyUsersByCompanyId(long companyId) {
        Company company = companyRepository.findCompanyById(companyId);
        return userRepository.findUsersByCompany(company);
    }

    /**
     * 队长根据名字搜索公司成员
     * @param username
     * @return
     */
    @Override
    public List<User> ListCompanyUsersByUsername(long leaderid,String username) {
        User leader = userRepository.findUserById(leaderid);
        Company company = companyRepository.findCompanyById(leader.getCompany().getId());
        return userRepository.findUsersByCompanyAndNameContaining(company,username);
    }

    /**
     * 发包方新建公司
     * @param company
     * @return
     */
    @Override
    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    /**
     * 发包方删除公司
     * @param companyId
     */
    @Override
    public void deleteCompanyByCompanyId(long companyId) {
        companyRepository.deleteCompanyById(companyId);
    }

    /**
     * 删除公司成员
     * @param companyId
     * @param userId
     */
    @Override
    public void deleteCompanyUser(long companyId, long userId) {
        Company company= companyRepository.findCompanyById(companyId);
        userRepository.deleteUserByCompanyAndId(company,userId);
    }
}
