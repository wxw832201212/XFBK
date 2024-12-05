package com.fast.qaManager.service.core.ctrl;


import com.fast.qaManager.service.core.buffer.LocalBuffer;
import com.fast.qaManager.service.core.entity.User;
import com.fast.qaManager.service.core.entity.vo.Response;
import com.fast.qaManager.service.core.service.UserService;
import com.fast.qaManager.service.core.util.ParamUtil;
import com.fast.qaManager.service.core.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.Map;


@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserCtrl {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public Response login(@RequestBody String body) {
        System.out.println("login:" + body);
        Map<String,Object> paramMap = ParamUtil.format(URLDecoder.decode(body));
        User user = new User();
        user.setStudentId(paramMap.get("studentId").toString());
        user.setUserName(paramMap.get("userName").toString());
        return userService.login(user);
    }

    @RequestMapping("/getUserByToken")
    @ResponseBody
    public Response getUserByToken(@RequestParam("token")String token) {

        return userService.getUserByToken(token);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Response logout(@RequestParam("token")String token) {
        LocalBuffer.loginUserBuffer.remove(token);
        return ResponseUtil.success();
    }

    @PostMapping("/updateUser")
    @ResponseBody
    public Response updateUser(@RequestParam("token")String token,@RequestBody String body) {
        Map<String,Object> paramMap = ParamUtil.format(URLDecoder.decode(body));
        return userService.updateUser(token,paramMap);
    }

}
