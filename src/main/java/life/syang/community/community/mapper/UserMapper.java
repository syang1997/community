package life.syang.community.community.mapper;

import life.syang.community.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insertUser(User user);

    @Select("select * from user where id=#{id}")
    User queryById(@Param("id") int id);

    @Select("select * from user where token=#{token}")
    User queryByToken(@Param("token") String token);

    @Select("select * from user where id=#{0}")
    User queryByCreater();

    @Select("select * from user where account_id=#{accountId}")
    User queryByAccountId(@Param("accountId") String accountId);

    @Update("update user set token=#{token} where account_id=#{accountId}")
    void updataToken(User user);
}
