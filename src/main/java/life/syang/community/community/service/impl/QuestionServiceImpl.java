package life.syang.community.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import life.syang.community.community.exception.CustomizeErrorCode;
import life.syang.community.community.exception.CustomizeException;
import life.syang.community.community.mapper.QuestionMapper;
import life.syang.community.community.model.Question;
import life.syang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Value("${myconfig.page-size}")
    private int pageSize;
    @Override
    public void insertQuestion(Question question) {
        questionMapper.insertQuestion(question);
    }

    @Override
    public PageInfo getQuestionList(int num) {
        PageHelper.startPage(num,pageSize);
        List<Question> list= questionMapper.getPageQuestion();
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public Question queryQuestionById(long id) {
        Question questionById = questionMapper.getQuestionById(id);
        if(questionById==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        return questionById;
    }

    @Override
    public void updateQuestion(Question question) {
        questionMapper.updateQuestion(question);
    }

    @Override
    public void increaseviewCount(long id) {
        questionMapper.increaseViewCount(id);
    }

    @Override
    public void increaseCommentCount(long id) {
        questionMapper.increaseCommentCount(id);
    }

    @Override
    public List<Question> queryLikeTagQuestion(Question question) {
        return questionMapper.queryLikeTagQuestion(question.getId(),question.getTag().replace(",","|"));
    }

    @Override
    public void updataQuestionTime(long gmtModified, long id) {
        questionMapper.updataQuestionTime(gmtModified,id);
    }

    @Override
    public PageInfo SearchQuestion(String search, int num) {
        PageHelper.startPage(num,pageSize);
        List<Question> list= questionMapper.querySearchQuestion(search);
        PageInfo page = new PageInfo(list);
        return page;
    }
}
