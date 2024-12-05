package com.fast.qaManager.service.core.service.impl;

import com.fast.qaManager.service.core.buffer.LocalBuffer;
import com.fast.qaManager.service.core.dao.InnerMessageDao;
import com.fast.qaManager.service.core.entity.InnerMessage;
import com.fast.qaManager.service.core.entity.Message;
import com.fast.qaManager.service.core.entity.MessageProblem;
import com.fast.qaManager.service.core.entity.User;
import com.fast.qaManager.service.core.entity.vo.Response;
import com.fast.qaManager.service.core.enums.ErrorType;
import com.fast.qaManager.service.core.exceptions.AssertException;
import com.fast.qaManager.service.core.service.InnerMessageService;
import com.fast.qaManager.service.core.util.Assert;
import com.fast.qaManager.service.core.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InnerMessageServiceImpl implements InnerMessageService {
    @Autowired
    private InnerMessageDao innerMessageDao;

    @Override
    public Response getInnerMessage(String token, Integer type) {
        try{
            User loginUser = LocalBuffer.loginUserBuffer.get(token);
            Assert.asserts(null != loginUser, ErrorType.USER_SESSION_IS_TIME_OUT);

            List<InnerMessage> innerMessages = innerMessageDao.findInnerMessagesByUserIdAndType(loginUser.getId(),type);
            return ResponseUtil.success("innerMessages",innerMessages);
        }catch (AssertException e){
            return ResponseUtil.assertError(e);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.systemError();
        }
    }
}
