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
    User findById(@Param("id") Integer id);

    @Select("SELECT COUNT(1) FROM user where account_id = #{accountId}")
    Integer findByAccountIdCount(@Param("accountId") String accountId);

    @Select("SELECT * FROM user where account_id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("UPDATE user set name=#{name},token=#{token},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where id=#{id}")
    void updateUser(User user);

}
