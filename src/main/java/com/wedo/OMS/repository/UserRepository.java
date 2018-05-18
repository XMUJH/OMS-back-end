package com.wedo.OMS.repository;

import com.wedo.OMS.entity.Company;
import com.wedo.OMS.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    User findUserById(Long userId);
    List<User> findUsersByCompany(Company company);
    List<User> findUsersByNameContaining(String name);
    void deleteUserByCompanyAndId(Company company,Long userId);
    int countUserByCompany(Company company);
}
