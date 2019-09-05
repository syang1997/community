package life.syang.community.community.mapper;

import life.syang.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,comment_count,view_count,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator.id},#{commentCount},#{viewCount},#{tag})")
    void insertQuestion(Question question);
}
