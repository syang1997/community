package life.syang.community.community.mapper;

import life.syang.community.community.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("insert into comment(parent_id,type,creator,gmt_create,gmt_modified,like_count,content) values(#{parentId},#{type},#{creator.id},#{gmtCreate},#{gmtModified},#{likeCount},#{content})")
    void insertComment(Comment comment);

    @Results(@Result(column = "creator",property = "creator",one = @One(select = "life.syang.community.community.mapper.UserMapper.queryByCreater")))
    @Select("select * from comment where parent_id=#{id} and type=1  ORDER BY gmt_create DESC")
    List<Comment> queryCommentByQuestionId(@Param("id") long id);

    @Results(@Result(column = "creator",property = "creator",one = @One(select = "life.syang.community.community.mapper.UserMapper.queryByCreater")))
    @Select("select * from comment where  type=2 and parent_id=#{id} ORDER BY gmt_create DESC")
    List<Comment> queryCommentByQuestionIdtwo(@Param("id") long id);

    @Results(@Result(column = "creator",property = "creator",one = @One(select = "life.syang.community.community.mapper.UserMapper.queryByCreater")))
    @Select("select * from comment where  type=3 and parent_id=#{id} ORDER BY gmt_create DESC")
    List<Comment> queryCommentByQuestionIdthree(@Param("id") long id);

    @Update("update comment set reply_count=reply_count+1 where id=#{id}")
    void incerCountTooneComment(@Param("id") long id);

    @Results(@Result(column = "creator",property = "creator",one = @One(select = "life.syang.community.community.mapper.UserMapper.queryByCreater")))
    @Select("select * from comment where id=#{id}")
    Comment queryCommentById(@Param("id") long id);

    @Select("select * from comment where id=#{0}")
    Comment queryByCreater();
}
