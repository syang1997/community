package life.syang.community.community.mapper;

import life.syang.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insertUser(User user);

    @Select("select * from user where account_id=#{id}")
    User queryById(@Param("id") String id);

    @Select("select * from user where token=#{token}")
    User queryByToken(@Param("token") String token);
}
