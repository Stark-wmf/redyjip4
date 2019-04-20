package com.redrock.weixin.entry;

import lombok.Data;

@Data
public class User {

    private String user_name;

    private int user_id;

    private String password;

    private String vote;

    private String last_login_time;

    private double score;

    private int status;
    //1为冻结，无法抽奖投票，2为用户被删除
}
