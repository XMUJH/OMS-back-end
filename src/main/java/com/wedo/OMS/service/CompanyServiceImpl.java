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

    @Override
    public Company getCompanyByUserId(Long userId) {
        User user = userRepository.findUserById(userId);
        return companyRepository.findCompanyById(user.getCompany().getId());
    }

    @Override
    public List<Company> listCompaniesByCompanyname(String companyname) {
        return companyRepository.findCompaniesByNameContaining(companyname);
    }

    @Override
    public List<User> listCompanyUsersByCompanyId(Long companyId) {
        Company company = companyRepository.findCompanyById(companyId);
        return userRepository.findUsersByCompany(company);
    }

    @Override
    public List<User> ListCompanyUsersByUsername(String username) {
        return userRepository.findUsersByNameContaining(username);
    }

    @Override
    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void deleteCompanyByCompanyId(Long companyId) {
        companyRepository.deleteCompanyById(companyId);
    }

    @Override
    public void deleteCompanyUser(Long companyId, Long userId) {

    }
}
