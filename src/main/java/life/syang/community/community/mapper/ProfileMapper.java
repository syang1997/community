package life.syang.community.community.mapper;

import life.syang.community.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProfileMapper {

    @Results(@Result(column = "creator",property = "creator",one = @One(select = "life.syang.community.community.mapper.UserMapper.queryByCreater")))
    @Select("select * from question where creator=#{id}")
    List<Question> queryById(@Param("id")int id);
}
