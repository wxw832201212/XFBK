package com.fast.qaManager.service.core.service;


import com.fast.qaManager.service.core.entity.LearningPlan;
import com.fast.qaManager.service.core.entity.vo.Response;

import java.util.Map;

public interface LearningPlanService {

    public Response addOrUpdateLearningPlan(String token, Map<String,Object> map);
}
