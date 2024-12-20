package com.fast.qaManager.service.core.dao;

import com.fast.qaManager.service.core.entity.LearningPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningPlanDao extends JpaRepository<LearningPlan, Integer> {
    LearningPlan findLearningPlanByUserId(Integer userId);
}
