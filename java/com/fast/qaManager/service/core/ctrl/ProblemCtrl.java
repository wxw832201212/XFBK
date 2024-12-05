package com.fast.qaManager.service.core.ctrl;

import com.fast.qaManager.service.core.entity.vo.Response;
import com.fast.qaManager.service.core.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
@RequestMapping("/problem")
public class ProblemCtrl {
    @Autowired
    private ProblemService problemService;


    @RequestMapping("/list")
    @ResponseBody
    public Response list(@RequestParam("token")String token) {
        return problemService.list(token);
    }

    @RequestMapping("/addProblem")
    @ResponseBody
    public Response addProblem(@RequestParam("token")String token,@RequestParam("topic")String topic) {
        return problemService.addProblem(token,topic);
    }
}
