package com.info.sms.mapper;

import com.info.sms.model.User;
import org.apache.ibatis.annotations.*;

/**
 * Created by Luke 2020/4/2 17:30
 */
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user(name,account_id,token,gmt_create,gmt_modified,avatar_url) VALUES(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("SELECT * FROM user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("SELECT * FROM user where id = #{id}")
//    @Results({
//            @Result(property = "accountId",column = "account_id"),
//            @Result(property = "gmtCreate",column = "gmt_create"),
//            @Result(property = "gmtModified",column = "gmt_modified"),
//            @Result(property = "avatarUrl",column = "avatar_url")
//    })
    User findById(@Param("id") Integer id);

}
