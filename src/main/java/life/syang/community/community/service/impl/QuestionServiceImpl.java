package life.syang.community.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
}
