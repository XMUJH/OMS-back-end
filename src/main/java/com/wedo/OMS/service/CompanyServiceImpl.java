package com.wedo.OMS.service;

import com.wedo.OMS.entity.Company;
import com.wedo.OMS.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Override
    public Company getCompanyByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Company> listCompanysByCompanyname(String companyname) {
        return null;
    }

    @Override
    public List<Company> listCompanyUsersByCompanyId(Long companyId) {
        return null;
    }

    @Override
    public List<User> ListCompanyUsersByUsername(String username) {
        return null;
    }

    @Override
    public Company addCompany(Company company) {
        return null;
    }

    @Override
    public void deleteCompanyByCompanyId(Long companyId) {

    }

    @Override
    public void deleteCompanyUser(Long companyId, Long userId) {

    }
}
