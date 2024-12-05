package com.fast.qaManager.service.core.service;

import com.fast.qaManager.service.core.entity.vo.Response;

public interface InnerMessageService {
    public Response getInnerMessage(String token,Integer type);
}
