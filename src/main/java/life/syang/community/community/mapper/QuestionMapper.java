package life.syang.community.community.mapper;

import life.syang.community.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,comment_count,view_count,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator.id},#{commentCount},#{viewCount},#{tag})")
    void insertQuestion(Question question);

    @Results(@Result(column = "creator",property = "creator",one = @One(select = "life.syang.community.community.mapper.UserMapper.queryByCreater")))
    @Select("select * from question")
    List<Question> getPageQuestion();

    @Results(@Result(column = "creator",property = "creator",one = @One(select = "life.syang.community.community.mapper.UserMapper.queryByCreater")))
    @Select("select * from question where id=#{id}")
    Question getQuestionById(@Param("id") long id);

    @Update("update question set title=#{title},description=#{description},tag=#{tag}, where id=#{id}")
    void updateQuestion(Question question);

    @Update("update question set view_count=view_count+1 where id=#{id}")
    void increaseViewCount(@Param("id") long id);

    @Update("update question set comment_count=comment_count+1 where id=#{id}")
    void increaseCommentCount(@Param("id") long id);

}
