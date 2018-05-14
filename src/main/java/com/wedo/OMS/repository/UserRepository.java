package com.wedo.OMS.repository;

import com.wedo.OMS.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    int countUserByCompany(String company);
}
