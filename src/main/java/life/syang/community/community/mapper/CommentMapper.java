package life.syang.community.community.mapper;

import life.syang.community.community.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("insert into comment(parent_id,type,creator,gmt_create,gmt_modified,like_count,content) values(#{parentId},#{type},#{creator.id},#{gmtCreate},#{gmtModified},#{likeCount},#{content})")
    void insertComment(Comment comment);

    @Results(@Result(column = "creator",property = "creator",one = @One(select = "life.syang.community.community.mapper.UserMapper.queryByCreater")))
    @Select("select * from comment where parent_id=#{id} and type=1")
    List<Comment> queryCommentByQuestionId(@Param("id") long id);

    @Results(@Result(column = "creator",property = "creator",one = @One(select = "life.syang.community.community.mapper.UserMapper.queryByCreater")))
    @Select("select * from comment where  type=2 and parent_id in (select id from comment where parent_id=#{id} and type=1) ")
    List<Comment> queryCommentByQuestionIdtwo(@Param("id") long id);

    @Results(@Result(column = "creator",property = "creator",one = @One(select = "life.syang.community.community.mapper.UserMapper.queryByCreater")))
    @Select("select * from comment where  type=3 and parent_id in (select creator from comment where  type=2 and parent_id in (select id from comment where parent_id=#{id} and type=1)) ")
    List<Comment> queryCommentByQuestionIdthree(@Param("id") long id);
}
