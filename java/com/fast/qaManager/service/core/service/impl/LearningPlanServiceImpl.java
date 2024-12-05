package com.fast.qaManager.service.core.service.impl;

import com.fast.qaManager.service.core.buffer.LocalBuffer;
import com.fast.qaManager.service.core.dao.InnerMessageDao;
import com.fast.qaManager.service.core.dao.LearningPlanDao;
import com.fast.qaManager.service.core.entity.InnerMessage;
import com.fast.qaManager.service.core.entity.LearningPlan;
import com.fast.qaManager.service.core.entity.User;
import com.fast.qaManager.service.core.entity.vo.Response;
import com.fast.qaManager.service.core.enums.ErrorType;
import com.fast.qaManager.service.core.exceptions.AssertException;
import com.fast.qaManager.service.core.service.LearningPlanService;
import com.fast.qaManager.service.core.util.Assert;
import com.fast.qaManager.service.core.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class LearningPlanServiceImpl implements LearningPlanService {
    @Autowired
    private LearningPlanDao learningPlanDao;

    @Autowired
    private InnerMessageDao innerMessagesDao;

    @Override
    public Response addOrUpdateLearningPlan(String token, Map<String, Object> map) {
        try{
            User loginUser = LocalBuffer.loginUserBuffer.get(token);
            Assert.asserts(null != loginUser, ErrorType.USER_SESSION_IS_TIME_OUT);

            //查看是否存在计划
            LearningPlan learningPlan = learningPlanDao.findLearningPlanByUserId(loginUser.getId());
            if(null == learningPlan) {
                learningPlan = new LearningPlan();
            }
            learningPlan.setUserId(loginUser.getId());
            learningPlan.setName(map.get("name").toString());
            learningPlan.setType(map.get("type").toString());
            learningPlan.setTarget(map.get("target").toString());
            learningPlan.setHour(Integer.parseInt(map.get("hour").toString()));
            learningPlan = learningPlanDao.saveAndFlush(learningPlan);
            //通知消息
            InnerMessage innerMessage = new InnerMessage();
            innerMessage.setTitle("学习计划已更新");
            innerMessage.setType(1);
            innerMessage.setContent("您的学习计划已调整，请查看最新进度。");
            innerMessage.setUserId(loginUser.getId());
            innerMessage.setSendTime(Instant.ofEpochMilli(new Date().getTime()));
            innerMessagesDao.saveAndFlush(innerMessage);

            InnerMessage innerMessage2 = new InnerMessage();
            innerMessage2.setTitle("本周复习提醒");
            innerMessage2.setType(1);
            innerMessage2.setContent("别忘了完成每周复习任务！");
            innerMessage2.setUserId(loginUser.getId());
            innerMessage2.setSendTime(Instant.ofEpochMilli(new Date().getTime()));
            innerMessagesDao.saveAndFlush(innerMessage2);

            return ResponseUtil.success();
        }catch (AssertException e){
            return ResponseUtil.assertError(e);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.systemError();
        }
    }
}
