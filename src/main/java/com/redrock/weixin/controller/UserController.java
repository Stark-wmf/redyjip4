package com.redrock.weixin.controller;

import com.alibaba.fastjson.JSONObject;
import com.redrock.weixin.entry.User;
import com.redrock.weixin.entry.Votelog;
import com.redrock.weixin.enums.ResultEnum;
import com.redrock.weixin.service.AwardService;
import com.redrock.weixin.service.UserService;
import com.redrock.weixin.util.Register;
import com.redrock.weixin.util.ResultUtil;
import com.redrock.weixin.util.ShiroUtil;
import com.redrock.weixin.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AwardService awardService;

    @GetMapping("/hello")
    public String index() {
        return "Hello World";
    }

    @PostMapping(value = "/registe")
    public Map<Integer, String> register(HttpServletRequest request) {
        Map<Integer, String> mapStatus = new HashMap<>();
        mapStatus.clear();
        User user = new User();
        String username = request.getParameter("username");
        Map<String,Object> map = userService.queryInfoByUsername(username);
        if(map != null){
            mapStatus.put(3, "注册失败，该用户已经存在");
        }
        String password = Register.register(username, request.getParameter("password"));
        if(StringUtil.isEmpty(username) ){

        }
        user.setUser_name(username);
        user.setPassword(password);
//        System.out.println(user);
        mapStatus = userService.insertUser(user);

        return mapStatus;

    }

    @PostMapping(value = "/doLogout",produces = "application/json")
    public JSONObject doLogout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return ResultUtil.success("退出成功");
    }

    //修改用户信息,修改后跳转到登录页面
    @PostMapping(value = "/modifyInfo", produces = "application/json")
    public JSONObject modifyInfo(String newUsername,String newPassword ,String password) {
        String user = ShiroUtil.getLoginUser();
        System.out.println(user);
        userService.modifyUserInfo(newUsername, newPassword,password, user);
        SecurityUtils.getSubject().logout();
        return ResultUtil.success();
    }

    /**
     * 登陆接口
     */
    @PostMapping(value = "/doLogin")
    public Map<String, Object> doLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> mapStatus = new HashMap<>();
        mapStatus.clear();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 创建Subject实例
        Subject currentUser = SecurityUtils.getSubject();
        // 将用户名及密码封装到UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        HttpSession session = request.getSession(false);
            try {
//                currentUser.login(token);
                //返回第二天该时间
                mapStatus.put("success",userService.queryUserByUsername(username));
                return mapStatus;

            } catch (AuthenticationException e) {
                e.printStackTrace();
                mapStatus.put("1", "登录失败，用户名或密码错误");
                return mapStatus;
            }
        }

    @PostMapping(value = "/vote")
    public JSONObject vote(int college_id){
        String username= ShiroUtil.getLoginUser();
        User user=userService.queryUserByUsername(username);
        int user_id = user.getUser_id();
        Votelog votelog =new Votelog(user_id,college_id);
        userService.vote(votelog);
        return ResultUtil.success();
    }

    @PostMapping(value = "/turn")
    public JSONObject turn(){
        String username= ShiroUtil.getLoginUser();
        User user=userService.queryUserByUsername(username);
        int user_id = user.getUser_id();
        awardService.turntable(user_id);
        return ResultUtil.success();
    }
    }

