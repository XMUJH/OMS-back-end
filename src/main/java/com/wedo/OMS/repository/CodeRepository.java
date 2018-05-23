package com.wedo.OMS.repository;

import com.wedo.OMS.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<Code, Long> {
    Code findCodeByCode(String code);
}
