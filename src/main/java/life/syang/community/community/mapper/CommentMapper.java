package life.syang.community.community.mapper;

import life.syang.community.community.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("insert into comment(parentId,type,commentator,gmtCreate,gmtModified,likeCount,content) values(#{parentId},#{type},#{commentator},#{gmtCreate},#{gmtModified},#{likeCount},#{content})")
    void insertComment(Comment comment);

    @Results(@Result(column = "creator",property = "creator",one = @One(select = "life.syang.community.community.mapper.UserMapper.queryByCreater")))
    @Select("select * from comment where parent_id=#{id} and type=#{type}")
    List<Comment> queryCommentByQuestionId(@Param("id") long id,@Param("type") int type);
}
