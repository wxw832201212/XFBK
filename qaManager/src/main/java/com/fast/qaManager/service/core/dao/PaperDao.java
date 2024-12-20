package com.fast.qaManager.service.core.dao;

import com.fast.qaManager.service.core.entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperDao extends JpaRepository<Paper, Integer> , JpaSpecificationExecutor<Paper> {
    Paper findPaperById(Integer id);
}
