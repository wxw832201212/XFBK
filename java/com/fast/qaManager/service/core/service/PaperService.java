package com.fast.qaManager.service.core.service;

import com.fast.qaManager.service.core.entity.vo.Response;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface PaperService {
    public Response list(String token);
    public Response listByParams(String token, Map<String,Object> map);
    public void download(HttpServletResponse httpServletResponse,String token, Integer paperId);

}
