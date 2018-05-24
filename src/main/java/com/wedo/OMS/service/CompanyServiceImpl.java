package com.wedo.OMS.service;

import com.wedo.OMS.entity.Company;
import com.wedo.OMS.entity.User;
import com.wedo.OMS.exception.CompanyNotFoundException;
import com.wedo.OMS.exception.UserNotFoundException;
import com.wedo.OMS.repository.CompanyRepository;
import com.wedo.OMS.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(UserRepository userRepository, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    /**
     * 根据用户ID获取用户公司
     *
     * @param userId
     * @return
     */
    @Override
    public Company getCompanyByUserId(long userId) throws UserNotFoundException, CompanyNotFoundException {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        Long companyId = user.getCompany().getId();
        Company company = companyRepository.findCompanyById(companyId);
        if (company == null) {
            throw new CompanyNotFoundException();
        }
        return company;
    }

    /**
     * 根据公司名搜索公司
     *
     * @param
     * @return
     */
    @Override
    public List<Company> listCompaniesByCompanyname(String companyName) {
        return companyRepository.findCompaniesByNameContaining(companyName);
    }

    /**
     * 发包方根据公司ID获取公司的所有成员
     *
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
     *
     * @param username
     * @return
     */
    @Override
    public List<User> ListCompanyUsersByUsername(long leaderid, String username) throws UserNotFoundException, CompanyNotFoundException {
        User leader = userRepository.findUserById(leaderid);
        if (leader == null) {
            throw new UserNotFoundException();
        }
        Company company = companyRepository.findCompanyById(leader.getCompany().getId());
        if (company == null) {
            throw new CompanyNotFoundException();
        }
        return userRepository.findUsersByCompanyAndNameContaining(company, username);
    }

    /**
     * 发包方新建公司
     *
     * @param company
     * @return
     */
    @Override
    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    /**
     * 发包方删除公司
     *
     * @param companyId
     */
    @Override
    public void deleteCompanyByCompanyId(long companyId) {
        companyRepository.deleteCompanyById(companyId);
    }

    /**
     * 管理员删除公司成员
     *
     * @param userId
     */
    @Override
    public void deleteCompanyUser(long userId) {
        userRepository.deleteById(userId);
    }
}
