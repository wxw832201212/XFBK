package com.fast.qaManager.service.core.dao;

import com.fast.qaManager.service.core.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemDao extends JpaRepository<Problem,Integer> {
    Problem findProblemById(Integer id);
}
