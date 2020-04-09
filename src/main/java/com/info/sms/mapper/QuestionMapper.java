package com.info.sms.mapper;

import com.info.sms.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Luke 2020/4/9 13:11
 */
@Mapper
public interface QuestionMapper {
    @Insert("inster into question (title,description,tag,gmt_create,creater,gmt_modified,modifier) values(#{title},#{description},#{tag},#{gmtCreate},#{creater},#{gmtModified},#{modifier}) ")
    void create(Question question);
}