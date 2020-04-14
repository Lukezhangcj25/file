package com.info.sms.mapper;

import com.info.sms.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Luke 2020/4/9 13:11
 */
@Mapper
public interface QuestionMapper {
    @Insert("INSERT INTO question (title,description,tag,gmt_create,creater,gmt_modified,modifier) VALUES(#{title},#{description},#{tag},#{gmtCreate},#{creater},#{gmtModified},#{modifier})")
    void create(Question question);

    @Select("SELECT * FROM question")
    List<Question> list();
}