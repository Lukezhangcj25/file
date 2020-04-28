package com.info.sms.mapper;


import com.info.sms.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Luke 2020/4/9 13:11
 */
@Mapper
public interface QuestionMapper {
    @Insert("INSERT INTO question (title,description,tag,gmt_create,creater,gmt_modified,modifier) VALUES(#{title},#{description},#{tag},#{gmtCreate},#{creater},#{gmtModified},#{modifier})")
    void create(Question question);

    @Select("SELECT * FROM question ORDER BY gmt_modified desc limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("SELECT COUNT(1) FROM question")
    Integer count();

    @Select("SELECT * FROM question where creater = #{userId} limit #{offset},#{size}")
    List<Question> listByUserId(@Param(value = "userId") int userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("SELECT COUNT(1) FROM question where creater = #{userId}")
    Integer countByUserId(@Param(value = "userId") int userId);

    @Select("SELECT * FROM question where id = #{id}")
    Question getById(@Param(value = "id") Integer id);

    @Update("UPDATE question SET title = #{title},description = #{description},tag = #{tag},modifier=#{modifier},gmt_modified=#{gmtModified} where id = #{id}")
    void update(Question question);
}