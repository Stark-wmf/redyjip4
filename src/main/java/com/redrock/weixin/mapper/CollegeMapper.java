package com.redrock.weixin.mapper;

import com.redrock.weixin.entry.College;
import com.redrock.weixin.entry.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CollegeMapper {

    @Insert(" INSERT INTO college SET college_name = #{college_name},info = #{info},voted=0")
    void add(College college);

    @Select("SELECT COUNT(college_id) FROM college WHERE college_id =#{0}}")
    int IfCollegeExist(int college_id);


}
