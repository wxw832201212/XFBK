package com.fast.qaManager.service.core.service;



import com.fast.qaManager.service.core.entity.User;
import com.fast.qaManager.service.core.entity.vo.Response;

import java.util.Map;

public interface UserService {
    public Response login(User user);
    public Response getUserByToken(String token);
    public Response updateUser(String token, Map<String,Object> map);

}

