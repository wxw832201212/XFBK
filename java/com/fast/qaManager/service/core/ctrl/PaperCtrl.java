package com.fast.qaManager.service.core.ctrl;

import com.fast.qaManager.service.core.entity.User;
import com.fast.qaManager.service.core.entity.vo.Response;
import com.fast.qaManager.service.core.service.PaperService;
import com.fast.qaManager.service.core.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/paper")
public class PaperCtrl {
    @Autowired
    private PaperService paperService;

    @RequestMapping("/list")
    @ResponseBody
    public Response list(@RequestParam("token")String token) {
        return paperService.list(token);
    }

    @PostMapping("/listByParams")
    @ResponseBody
    public Response listByParams(@RequestParam("token")String token,@RequestBody String json) {
        Map<String,Object> map = ParamUtil.format(URLDecoder.decode(json));
        return paperService.listByParams(token,map);
    }



    @RequestMapping("/download")
    public void download(HttpServletResponse httpServletResponse, @RequestParam("token")String token, @RequestParam("paperId")Integer paperId) {
        paperService.download(httpServletResponse, token, paperId);
    }}
