package com.redrock.weixin.enums;

/**
 * 返回的状态码
 */
public enum ResultEnum {

    //100 开头表示成功
    OK(100001, "请求成功"),

    //200 开头表示用户错误
    NOT_LOGIN(200001, "用户未登录"),
    LOGIN_ERROR(200002, "账号或者密码错误"),
    WRONG_CODE(200011, "验证码错误"),
    NAME_IS_REGIST(200013,"用户存在"),
    FREECED(200014,"用户被冻结"),
    DELETED(200015,"用户被删除"),
    //300
    UNKNOW_ERROR(300001, "未知错误"),
    //400 开头表示参数错误
    PARAM_ERROR(400004, "参数错误"),
    //500开头表示服务器错误
    DATABASE_ERROR(500001, "数据库错误");


    private int code;
    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
