package com.redrock.weixin.util;

import com.redrock.weixin.enums.ResultEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

public class ShiroUtil {

    /**
     * 获取当前登录用户
     * @return
     */
    public static String getLoginUser(){
        Subject subject = SecurityUtils.getSubject();
        String username = null;
        try {
            username = (String)subject.getPrincipal();
        } catch (Exception e) {
            e.printStackTrace();
         //   throw new DefinedException(ResultEnum.NOT_LOGIN);
        }
        return username;
    }


    //暂时修改subject用户名
    public static void modifyUserInfo(String newPhone){
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realmName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection =
                new SimplePrincipalCollection(newPhone, realmName);
        subject.runAs(newPrincipalCollection);

    }
}