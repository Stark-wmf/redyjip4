package com.redrock.weixin.mapper;

import com.redrock.weixin.entry.User;
import com.redrock.weixin.entry.Votelog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface VoteMapper {
    @Insert(" INSERT INTO votelog SET user_id = #{user_id},college_id=#{college_id}")
    void vote(Votelog votelog);

    @Insert(" INSERT INTO college SET voted = voted+1,college_id=#{college_id}")
    void votecollege(Votelog votelog);


//    @Select("SELECT COUNT(college_id) FROM votelog v,college c WHERE vid =c.college_id AND c.college_id =#{0}")
//    int IfMeetsExist(int college_id);
}
