package com.fast.qaManager.service.core.service;

import com.fast.qaManager.service.core.entity.vo.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProblemService {
    public Response list(String token);
    public Response addProblem(String token,@RequestParam("topic") String topic);
}
