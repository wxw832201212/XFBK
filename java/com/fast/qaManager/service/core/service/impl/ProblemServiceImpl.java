package com.fast.qaManager.service.core.service.impl;

import com.fast.qaManager.service.core.buffer.LocalBuffer;
import com.fast.qaManager.service.core.dao.ProblemDao;
import com.fast.qaManager.service.core.entity.Paper;
import com.fast.qaManager.service.core.entity.Problem;
import com.fast.qaManager.service.core.entity.User;
import com.fast.qaManager.service.core.entity.vo.Response;
import com.fast.qaManager.service.core.enums.ErrorType;
import com.fast.qaManager.service.core.exceptions.AssertException;
import com.fast.qaManager.service.core.service.ProblemService;
import com.fast.qaManager.service.core.util.Assert;
import com.fast.qaManager.service.core.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemDao problemDao;

    @Override
    public Response list(String token) {
        try{
            User loginUser = LocalBuffer.loginUserBuffer.get(token);
            Assert.asserts(null != loginUser, ErrorType.USER_SESSION_IS_TIME_OUT);

            List<Problem> problems = problemDao.findAll();

            return ResponseUtil.success("problems",problems);
        }catch (AssertException e){
            return ResponseUtil.assertError(e);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.systemError();
        }
    }

    @Override
    public Response addProblem(String token, String topic) {
        try{
            User loginUser = LocalBuffer.loginUserBuffer.get(token);
            Assert.asserts(null != loginUser, ErrorType.USER_SESSION_IS_TIME_OUT);

            Problem problem = new Problem();
            problem.setTopic(topic);
            problem.setBrowseCount(0);
            problem.setUserId(loginUser.getId());
            problem = problemDao.saveAndFlush(problem);

            return ResponseUtil.success("problem",problem);
        }catch (AssertException e){
            return ResponseUtil.assertError(e);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.systemError();
        }
    }
}
