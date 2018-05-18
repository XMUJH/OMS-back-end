package com.wedo.OMS.repository;

import com.wedo.OMS.entity.Company;
import com.wedo.OMS.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
<<<<<<< HEAD
    User findUserById(Long userId);
    List<User> findUsersByCompany(Company company);
    List<User> findUsersByNameContaining(String name);
    int countUserByCompany(String company);
=======

    int countUserByCompany(Company company);
>>>>>>> 4794e3347754cdf3015c1a1c8339b3196e413df2
}
