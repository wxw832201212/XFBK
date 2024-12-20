package com.fast.qaManager.service.core.ctrl;

import com.fast.qaManager.service.core.entity.vo.Response;
import com.fast.qaManager.service.core.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
@RequestMapping("/message")
public class MessageCtrl {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/getMessage")
    @ResponseBody
    public Response getMessage(@RequestParam("token")String token,@RequestParam("problemId")Integer problemId) {
        return messageService.getMessage(token, problemId);
    }

    @RequestMapping("/sendMessage")
    @ResponseBody
    public Response sendMessage(@RequestParam("token")String token,@RequestParam("problemId")Integer problemId,@RequestParam("content")String content) {
        return messageService.sendMessage(token, problemId, content);
    }
}
