package life.syang.community.community.service.impl;

import life.syang.community.community.mapper.QuestionMapper;
import life.syang.community.community.model.Question;
import life.syang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Override
    public void insertQuestion(Question question) {
        questionMapper.insertQuestion(question);
    }
}
