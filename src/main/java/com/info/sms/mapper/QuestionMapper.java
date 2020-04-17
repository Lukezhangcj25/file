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

    @Select("SELECT * FROM question limit #{offset},#{size}")
//    @Results({
//            @Result(property = "commentCount", column = "comment_count"),
//            @Result(property = "viewCount", column = "view_count"),
//            @Result(property = "likeCount", column = "like_count"),
//            @Result(property = "gmtCreate", column = "gmt_create"),
//            @Result(property = "gmtModified", column = "gmt_modified")
//    })
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);


    @Select("SELECT COUNT(1) FROM question")
    Integer count();
}