package com.fast.qaManager.service.core.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.fast.qaManager.service.core.buffer.LocalBuffer;
import com.fast.qaManager.service.core.dao.UserDao;
import com.fast.qaManager.service.core.entity.User;
import com.fast.qaManager.service.core.entity.vo.Response;
import com.fast.qaManager.service.core.enums.ErrorType;
import com.fast.qaManager.service.core.exceptions.AssertException;
import com.fast.qaManager.service.core.service.UserService;
import com.fast.qaManager.service.core.util.Assert;
import com.fast.qaManager.service.core.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public Response login(User user) {
        try{
            Assert.asserts(null != user.getUserName(), ErrorType.USER_NAME_IS_NULL);
            Assert.asserts(null != user.getStudentId(),ErrorType.USER_STUDENT_IS_NULL);
            System.out.println("login user:" + URLDecoder.decode(user.getUserName()));
            User userDb = userDao.findUserByStudentIdAndUserName(user.getStudentId(), URLDecoder.decode(user.getUserName()));
            System.out.println("login userDB" + JSONObject.toJSONString(userDb));
            Assert.asserts(null != userDb,ErrorType.USER_NAME_AND_STUDENT_ID_IS_NULL);

            String token = UUID.randomUUID().toString();
            LocalBuffer.loginUserBuffer.put(token,userDb);
            return ResponseUtil.success("user",userDb,"token",token);
        }catch (AssertException e){
            return ResponseUtil.error(e.getErrorType());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.systemError();
        }
    }

    @Override
    public Response getUserByToken(String token) {
        try{
            User loginUser = LocalBuffer.loginUserBuffer.get(token);
            Assert.asserts(null != loginUser, ErrorType.USER_SESSION_IS_TIME_OUT);

            return ResponseUtil.success("user",loginUser);
        }catch (AssertException e){
            return ResponseUtil.error(e.getErrorType());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.systemError();
        }
    }

    @Override
    public Response updateUser(String token, Map<String,Object> map) {
        try{
            User loginUser = LocalBuffer.loginUserBuffer.get(token);
            Assert.asserts(null != loginUser, ErrorType.USER_SESSION_IS_TIME_OUT);

            User userDb = userDao.findUserByStudentIdAndUserName(loginUser.getStudentId(), URLDecoder.decode(loginUser.getUserName()));
            userDb.setSex(URLDecoder.decode(map.get("sex").toString()));
            userDb.setPet(URLDecoder.decode(map.get("pet").toString()));
            userDb = userDao.saveAndFlush(userDb);

            LocalBuffer.loginUserBuffer.put(token,userDb);

            return ResponseUtil.success("user",userDb);
        }catch (AssertException e){
            return ResponseUtil.error(e.getErrorType());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.systemError();
        }
    }
}
