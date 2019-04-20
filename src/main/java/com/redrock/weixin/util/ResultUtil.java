package com.redrock.weixin.util;

import com.alibaba.fastjson.JSONObject;

import com.redrock.weixin.enums.ResultEnum;

public class ResultUtil {

    /**
     * 返回成功
     * @param data
     * @return
     */
    public static JSONObject success(Object data){
        JSONObject json = new JSONObject();
        json.put("code", ResultEnum.OK.getCode());
        json.put("msg", "操作成功");
        json.put("data", data);
        return json;
    }

    /**
     * 返回成功
     * @return
     */
    public static JSONObject success(){
        JSONObject json = new JSONObject();
        json.put("code", ResultEnum.OK.getCode());
        json.put("msg", "操作成功");
        json.put("data", null);
        return json;
    }

    /**
     * 返回失败
     * @param resultEnum    状态码枚举类
     * @return
     */
    public static JSONObject fail(ResultEnum resultEnum){
        JSONObject json = new JSONObject();
        json.put("code", resultEnum.getCode());
        json.put("msg", resultEnum.getMsg());
        json.put("data", null);
        return json;
    }

    /**
     * 返回失败
     * @return
     */
    public static JSONObject fail(Integer code, String msg){
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        json.put("data", null);
        return json;
    }
}
