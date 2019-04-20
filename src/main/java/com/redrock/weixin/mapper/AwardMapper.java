package com.redrock.weixin.mapper;

import com.redrock.weixin.entry.Award;
import com.redrock.weixin.entry.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mapper
@Component
public interface AwardMapper {
    @Select("Select * from award ")
    List<Award> getAward ();

}
