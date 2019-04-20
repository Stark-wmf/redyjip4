package com.redrock.weixin.service;

import com.redrock.weixin.entry.Award;
import com.redrock.weixin.mapper.AwardMapper;
import com.redrock.weixin.mapper.CollegeMapper;
import com.redrock.weixin.mapper.UserMapper;
import com.redrock.weixin.mapper.VoteMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AwardService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CollegeMapper collegeMapper;
    @Autowired
    private VoteMapper voteMapper;
    @Autowired
    private AwardMapper awardMapper;



    public static void sum(List<Award> awards){
        double num = 0;
        for(Award award:awards){
            num += award.getA_probability();
            award.setA_probability(num);
        }
    }

    public static  int range(List<Award> awards){
        double random = Math.random();
        for (int i = 0;i <= awards.size();i++){
            double left = 0;
            double right = 0;
            if (i == 0){
                left = 0;
            }else {
                left = awards.get(i - 1).getA_probability();
            }
            right = awards.get(i).getA_probability();
            if (random >= left && random < right){
                return i;
            }
        }
        return -1;
    }

    public void turntable(int user_id){
        if(userMapper.getScore(user_id)<=0){

        }
        userMapper.turn(user_id);
        String user_name=userMapper.getUsername(user_id);
        List<Award> awards = new ArrayList<>();
//
//        for(int i = 0;i <= awards.size();i++){
//            Award award =new Award();
//            award=awards.get(i);
//            double[] arr1 =new double[5];
//            double arr1[i]=award.getA_probability();
//        }
       awards=awardMapper.getAward();
        sum(awards);
        int i =range(awards);
        System.out.println(user_name+"抽到了"+awards.get(i).getA_name());
    }
}
