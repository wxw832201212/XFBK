package com.fast.qaManager.service.core.service;

import com.fast.qaManager.service.core.entity.vo.Response;

public interface MessageService {
    public Response getMessage(String token,Integer problemId);
    public Response sendMessage(String token,Integer problemId,String content);
}
