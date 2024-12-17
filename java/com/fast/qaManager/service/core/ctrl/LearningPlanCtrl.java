//plan
package com.fast.qaManager.service.core.ctrl;

import com.fast.qaManager.service.core.entity.vo.Response;
import com.fast.qaManager.service.core.service.LearningPlanService;
import com.fast.qaManager.service.core.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/learningPlan")
public class LearningPlanCtrl {
    @Autowired
    private LearningPlanService learningPlanService;


    @PostMapping("/addOrUpdateLearningPlan")
    @ResponseBody
    public Response addOrUpdateLearningPlan(@RequestParam("token")String token, @RequestBody String json) {
        Map<String,Object> map = ParamUtil.format(URLDecoder.decode(json));
        return learningPlanService.addOrUpdateLearningPlan(token, map);
    }
}
