package com.redrock.weixin.controller;

import com.alibaba.fastjson.JSONObject;
import com.redrock.weixin.entry.User;
import com.redrock.weixin.service.UserService;
import com.redrock.weixin.util.ResultUtil;
import com.redrock.weixin.util.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/deleteUser")
    public JSONObject deleteUser(String username){
   userService.deleteUser(username);
        return ResultUtil.success();
    }

    @PostMapping(value = "/freeceUser")
    public JSONObject freeceUser(String username){
        userService.freeceUser(username);
        return ResultUtil.success();
    }
}
