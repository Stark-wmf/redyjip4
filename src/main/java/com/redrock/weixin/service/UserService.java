package com.redrock.weixin.service;

import com.redrock.weixin.entry.User;
import com.redrock.weixin.entry.Votelog;
import com.redrock.weixin.enums.ResultEnum;
import com.redrock.weixin.exception.DefinedException;
import com.redrock.weixin.mapper.CollegeMapper;
import com.redrock.weixin.mapper.UserMapper;
import com.redrock.weixin.mapper.VoteMapper;
import com.redrock.weixin.util.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CollegeMapper collegeMapper;
    @Autowired
    private VoteMapper voteMapper;

    @Transactional
    public Map<Integer, String> insertUser(User user) {
        int insert = userMapper.register(user);
        Map<Integer, String> mapStatus = new HashMap<>();
        if (insert != 0) {
            mapStatus.put(0, "注册成功");
        } else {
            mapStatus.put(1, "注册失败");
        }
        return mapStatus;
    }

    @Transactional
    public Map<String, Object> queryInfoByUsername(String username) {
        User user = userMapper.getPassword(username);
        HashMap<String, Object> map = null;
        if (user != null) {
            map = new HashMap<>();
            map.put("username", user.getUser_name());
            map.put("password", user.getPassword());
        } else {
             throw new DefinedException(ResultEnum.PARAM_ERROR,"该用户不存在");
        }
        return map;
    }

    @Transactional
    public User queryUserByUsername(String username) {
        User user = null;
        try {
            user = userMapper.getPassword(username);
        } catch (Exception e) {
            e.printStackTrace();
               throw new DefinedException(ResultEnum.DATABASE_ERROR);
        }
        return user;
    }

    /**
     * 修改用户名，密码需要跟新，所以要确认密码
     *
     * @param newUsername
     * @param password
     * @param username
     */
    @Transactional(rollbackFor = {IllegalArgumentException.class})
    public void modifyUserInfo(String newUsername, String newPassword, String password, String username) {
        User user1 = userMapper.getPassword(newUsername);
        //当前登录用户信息
        User currUser = userMapper.getPassword(username);
        //判断新用户名是否已经存在
        if (user1 != null) {
              throw new DefinedException(ResultEnum.NAME_IS_REGIST);
        }
        //判断密码是否正确
        if (!currUser.getPassword().equals(Register.register(username, password))) {
             throw new DefinedException(ResultEnum.LOGIN_ERROR, "密码错误");
        }
        //将密码重新加密
        String newPass = Register.register(newUsername, password);
        try {
            userMapper.modifyUserInfo(newUsername, newPassword, username);
        } catch (Exception e) {
            e.printStackTrace();
               throw new DefinedException(ResultEnum.DATABASE_ERROR);
        }
    }
    @Transactional(rollbackFor = {IllegalArgumentException.class})
    public void vote(Votelog votelog) {
        if (userMapper.IfUserExist(votelog.getUser_id()) == 0) {
               throw new DefinedException(ResultEnum.PARAM_ERROR,"用户不存在");
               }
        if(userMapper.getStatus(votelog.getUser_id())==1){
            throw new DefinedException(ResultEnum.FREECED);
        }
        if(userMapper.getStatus(votelog.getUser_id())==2){
            throw new DefinedException(ResultEnum.DELETED);
        }
            if (collegeMapper.IfCollegeExist(votelog.getCollege_id()) == 0) {
                   throw new DefinedException(ResultEnum.PARAM_ERROR,"投票的学院不存在");
            }
            if(userMapper.getVote(votelog.getUser_id())<=1){

            }
            voteMapper.vote(votelog);
            userMapper.score(votelog.getUser_id());
        }
    @Transactional(rollbackFor = {IllegalArgumentException.class})
    public void deleteUser(String username) {
        int user_id = userMapper.getPassword(username).getUser_id();
        //判断该用户是否已经存在
        if (userMapper.IfUserExist(user_id) == 0) {
            throw new DefinedException(ResultEnum.PARAM_ERROR, "该用户不存在");
        }
        if(userMapper.getStatus(user_id)==2){
            throw new DefinedException(ResultEnum.DELETED,"用户已被删除");
        }
        userMapper.deleteUser(username);
    }

    @Transactional(rollbackFor = {IllegalArgumentException.class})
    public void freeceUser(String username) {
        int user_id = userMapper.getPassword(username).getUser_id();
        //判断该用户是否已经存在
        if (userMapper.IfUserExist(user_id) == 0) {
            throw new DefinedException(ResultEnum.PARAM_ERROR, "该用户不存在");
        }
        if(userMapper.getStatus(user_id)==2){
            throw new DefinedException(ResultEnum.DELETED,"用户已被删除");
        }
        if(userMapper.getStatus(user_id)==1){
            throw new DefinedException(ResultEnum.FREECED,"用户已被冻结");
        }
        userMapper.freece(user_id);
    }
    }

