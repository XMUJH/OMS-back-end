package com.wedo.OMS.repository;

import com.wedo.OMS.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findCompanyById(Long companyId);
    List<Company> findCompaniesByNameContaining(String companyName);
    @Transactional
    void deleteCompanyById(Long companyId);
}
