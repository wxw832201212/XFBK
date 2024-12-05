package com.fast.qaManager.service.core.ctrl;

import com.fast.qaManager.service.core.entity.vo.Response;
import com.fast.qaManager.service.core.service.InnerMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
@RequestMapping("/innerMessage")
public class InnerMessageCtrl {
    @Autowired
    private InnerMessageService innerMessageService;

    @RequestMapping("/getInnerMessage")
    @ResponseBody
    public Response getInnerMessage(@RequestParam("token")String token, @RequestParam("type")Integer type) {
        return innerMessageService.getInnerMessage(token, type);
    }
}
