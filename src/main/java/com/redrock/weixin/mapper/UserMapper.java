package com.redrock.weixin.mapper;

import com.redrock.weixin.entry.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Component
public interface UserMapper {

    @Insert(" INSERT INTO user SET user_id = #{user_id},vote = 5, last_login_time = #{last_login_time}")
    int register(User user);

    @Select("Select * from user where user_name = #{user_name}")
    User getPassword(String username);

    @Select("Select user_name from user where user_id = #{0}")
    String getUsername(int user_id);

    @Update("update user set user_name=#{0},password=#{1} where user_name=#{2}")
    void modifyUserInfo(String newName,String newPassword,String username);

    @Select("SELECT COUNT(user_id) FROM user WHERE user_id =#{0}}")
    int IfUserExist(int user_id);

    @Select("Select vote from user where user_id = #{0}")
    int getVote(int user_id);

    @Select("Select score from user where user_id = #{0}")
    int getScore(int user_id);

    @Select("Select status from user where user_id = #{0}")
    int getStatus(int user_id);

    @Update("UPDATE user SET score=score-1 where user_id={0}")
    void turn(int user_id);

    @Insert(" INSERT INTO user SET score=score+1  WHERE user_id={0}")
    void score(int user_id);

    @Update("UPDATE user SET status=2 where user_name={0}")
    void  deleteUser(String user_name);

    @Update("UPDATE user SET status=1 where user_id={0}")
    void freece(int user_id);
}
