package com.redrock.weixin.entry;

import lombok.Data;

@Data
public class Votelog {
    private int user_id;

    private int college_id;

    private int vid;

    public Votelog(int user_id,int college_id){
        this.user_id=user_id;
        this.college_id=college_id;
    }


}
