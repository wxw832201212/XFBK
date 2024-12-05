package com.fast.qaManager.service.core.service.impl;

import com.fast.qaManager.service.core.buffer.LocalBuffer;
import com.fast.qaManager.service.core.dao.InnerMessageDao;
import com.fast.qaManager.service.core.dao.MessageDao;
import com.fast.qaManager.service.core.dao.MessageProblemDao;
import com.fast.qaManager.service.core.dao.ProblemDao;
import com.fast.qaManager.service.core.entity.*;
import com.fast.qaManager.service.core.entity.vo.Response;
import com.fast.qaManager.service.core.enums.ErrorType;
import com.fast.qaManager.service.core.exceptions.AssertException;
import com.fast.qaManager.service.core.service.MessageService;
import com.fast.qaManager.service.core.util.Assert;
import com.fast.qaManager.service.core.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    @Autowired
    private MessageProblemDao messageProblemDao;

    @Autowired
    private InnerMessageDao innerMessageDao;

    @Autowired
    private ProblemDao problemDao;

    @Override
    public Response getMessage(String token, Integer problemId) {
        try{
            User loginUser = LocalBuffer.loginUserBuffer.get(token);
            Assert.asserts(null != loginUser, ErrorType.USER_SESSION_IS_TIME_OUT);

            //检查是否存在加入记录，没有就加入
            MessageProblem messageProblem = messageProblemDao.findMessageProblemByProblemIdAndUserId(problemId, loginUser.getId());
            if(null == messageProblem) {
                messageProblem = new MessageProblem();
                messageProblem.setUserId(loginUser.getId());
                messageProblem.setProblemId(problemId);
                messageProblem = messageProblemDao.saveAndFlush(messageProblem);
            }

            List<Message> messages = messageDao.findMessagesByProblemId(problemId);

            return ResponseUtil.success("messages",messages);
        }catch (AssertException e){
            return ResponseUtil.assertError(e);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.systemError();
        }
    }

    @Override
    public Response sendMessage(String token, Integer problemId, String content) {
        try{
            User loginUser = LocalBuffer.loginUserBuffer.get(token);
            Assert.asserts(null != loginUser, ErrorType.USER_SESSION_IS_TIME_OUT);

            Problem problem = problemDao.findProblemById(problemId);

            //添加次数
            problem.setBrowseCount(problem.getBrowseCount() + 1);
            problem = problemDao.saveAndFlush(problem);

            //加入消息
            Message message = new Message();
            message.setSendTime(Instant.ofEpochMilli(new Date().getTime()));
            message.setProblemId(problemId);
            message.setContent(content);
            message.setUserName(loginUser.getUserName());
            message = messageDao.saveAndFlush(message);

            //通知参与此问题的成员,排除自身
            List<MessageProblem> messageProblems = messageProblemDao.findMessageProblemsByProblemIdAndUserIdNot(problemId, loginUser.getId());

            for(MessageProblem messageProblem : messageProblems) {
                InnerMessage innerMessage = new InnerMessage();
                innerMessage.setUserId(messageProblem.getUserId());
                innerMessage.setSendTime(Instant.ofEpochMilli(new Date().getTime()));
                innerMessage.setType(0);//问答类型
                innerMessage.setTitle("问题已收到回答");
                innerMessage.setContent("您的问题‘" + problem.getTopic() + "’有了新的回答。");
                innerMessageDao.saveAndFlush(innerMessage);
            }
            return ResponseUtil.success("message",message);
        }catch (AssertException e){
            return ResponseUtil.assertError(e);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.systemError();
        }
    }
}
