package com.wedo.OMS.repository;

import com.wedo.OMS.entity.Company;
import com.wedo.OMS.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    User findUserByAccount(String account);
    User findUserById(Long userId);
    List<User> findUsersByCompany(Company company);
    List<User> findUsersByNameContaining(String name);
    List<User> findUsersByCompanyAndNameContaining(Company company,String name);
    int countUserByCompany(Company company);
    @Transactional
    void deleteUserByCompanyAndId(Company company,Long userId);
}
